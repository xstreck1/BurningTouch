package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class DynamicPaper extends BasicPaper {
    Pixmap current_mask_pix_;
    ShapeRenderer renderer;

    int delay = 0;

    final int DIAMETER = 60;
    final int JITTER = 10;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);

    boolean has_changed = false;

    private float heat = 0f;
    private final float HEAT_INCREASE = 2.5f;
    private final float HEAT_DECREASE = 1.2f;
    private final float MOVE_DECREASE = 1.6f;
    private final float SHOW_THRESHOLD = 0.4f;
    private final float SHOW_STEP = 100f;
    private final float BURN_THRESHOLD = 0.9f;
    private final float BURN_STEP = 150f;
    private final float HEAT_MAX = 2.f;

    private final float HEAT_R = 0.4f;
    private final float HEAT_G = 0.1f;
    private final float HEAT_B = 0.1f;	    
    private final float HEAT_A = 0.3f;	
    private final float HEAT_SIZE = 0.15f;
    private final int HEAT_LAYERS = 10;
    
    private int last_x = 0;
    private int last_y = 0;

    DynamicPaper(Pixmap current_mask_pix_) {
	super();
	this.current_mask_pix_ = current_mask_pix_;
	renderer = new ShapeRenderer();
	resume();
    }

    public void resume() {
	this.current_mask_ = new Texture(current_mask_pix_);
	current_mask_pix_.setBlending(Pixmap.Blending.None);
    }

    int burnColorPart(int color_part) {
	return Math.max(0, Math.round(color_part - Math.max(0, heat - BURN_THRESHOLD) * BURN_STEP * Gdx.graphics.getDeltaTime()));
    }

    /**
     * Increase the value of the color pixels and alpha based on the heat.
     */
    private int morphPixel(int pixel) {
	int r = (pixel & 0xFF000000) >>> 24;
	int g = (pixel & 0x00FF0000) >>> 16;
	int b = (pixel & 0x0000FF00) >>> 8;
	int a = pixel & 0x000000FF;

	r = burnColorPart(r);
	g = burnColorPart(g);
	b = burnColorPart(b);
	a = Math.min(255, Math.round(a + Math.max(0, heat - SHOW_THRESHOLD) * SHOW_STEP * Gdx.graphics.getDeltaTime()));

	r <<= 24;
	g <<= 16;
	b <<= 8;
	return r | g | b | a;
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	// Change the heat based on the latest movement
	heat = Math.min(heat + (Gdx.graphics.getDeltaTime() * HEAT_INCREASE), HEAT_MAX);
	float distance = (float) Math.sqrt((last_x - mouse_x) * (last_x - mouse_x) + (last_y - mouse_y) * (last_y - mouse_y));
	heat = Math.max(0f, heat - distance * MOVE_DECREASE * Gdx.graphics.getDeltaTime());

	// Update the pixels based on the last position and their diametere from
	// there
	for (int x = Math.max(0, mouse_x - DIAMETER / 2); x < Math.min(Constants.GAME_WIDTH, mouse_x + DIAMETER / 2); x++) {
	    int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouse_x) * (x - mouse_x))));
	    for (int y = Math.max(0, mouse_y - span); y < Math.min(Constants.GAME_HEIGHT, mouse_y + span); y++) {
		double prob = ((DIAMETER / 2) - Math.sqrt((last_x - x) * (last_x - x) + (last_y - y) * (last_y - y))) / JITTER;
		if (prob > 1f || prob > MathUtils.random())
		    current_mask_pix_.drawPixel(x, y, morphPixel(current_mask_pix_.getPixel(x, y)));
	    }
	}
	current_mask_.draw(current_mask_pix_, 0, 0);

	last_x = mouse_x;
	last_y = mouse_y;
    }

    @Override
    public void update() {
	heat = Math.max(heat - (Gdx.graphics.getDeltaTime() * HEAT_DECREASE), 0);
    }

    @Override
    public void draw(SpriteBatch batch) {
	super.draw(batch);
	
    }
    
    @Override
    public void drawHeat(OrthographicCamera cam) {
	if (GameState.mouse_pressed) {
	    renderer.setProjectionMatrix(cam.combined);
	    renderer.begin(ShapeType.Filled);
	    float factor = Math.max(0,  heat - SHOW_THRESHOLD);
	    renderer.setColor(HEAT_R * factor, HEAT_G * factor, HEAT_B * factor, (float) HEAT_A * factor / HEAT_LAYERS);
	    for (int i = 0; i < HEAT_LAYERS; i++)
		renderer.circle(last_x, Constants.GAME_HEIGHT - last_y, (DIAMETER / 2f) * (1 + HEAT_SIZE * i));
	    renderer.end();
	}
    }
}
