package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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
    Texture burned_paper = null;
    Texture finished_paper = null;

    // Heat space coverage
    final int DIAMETER = 60;
    final int JITTER = 10;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);

    // Heat pixel manipulation
    private float heat = 0f;
    private final float HEAT_INCREASE = 2.00f;
    private final float HEAT_DECREASE = 1.0f;
    private final float MOVE_DECREASE = 1.0f;
    private final int   MAX_MOVE_DISTANCE = 500;
    private final float SHOW_THRESHOLD = 0.5f;
    private final float SHOW_STEP = 100f;
    private final float BURN_THRESHOLD = 1.5f;
    private final float BURN_STEP = 100f;
    private final float HEAT_MAX = 3.5f;

    // Heat hint circle properties
    private final float HEAT_R = 0.30f;
    private final float HEAT_G = 0.05f;
    private final float HEAT_B = 0.05f;
    private final float HEAT_A = 0.30f;
    private final float HEAT_SIZE = 0.25f;
    private final int HEAT_LAYERS = 10;

    // Last touch
    private int last_x = 0;
    private int last_y = 0;

    // State change
    private int uncovered = 0;
    private int burned = 0;
    // How many alpha points are required.
    private final int UNCOVER_REQ = Math.round(Constants.GAME_HEIGHT * Constants.GAME_WIDTH * 2.5f * Constants.CLEAR_TRHS * 100f);
    // How much of the burn is required
    private final int BURN_REQ = Math.round((DIAMETER - JITTER) * (DIAMETER - JITTER) * .75f * Constants.BURN_TRHS);

    // Afterburn
    private int burning_radius = DIAMETER / 2 - JITTER;
    private int max_radius = Constants.GAME_WIDTH;

    // Animations
    private final float BURN_TIME = 2.5f;
    private float burning = 0;
    private final float CLEAR_TIME = 5f;
    private float clearing = 0;

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

    public void pause() {
	this.current_mask_.dispose();
    }
    
    int burnColorPart(int color_part) {
	return Math.max(0, Math.round(color_part - (heat > BURN_THRESHOLD ? BURN_STEP : 0) * Gdx.graphics.getDeltaTime()));
    }

    /**
     * Increase the value of the color pixels and alpha based on the heat.
     */
    private int morphPixel(int pixel) {
	if ((pixel & 0xFFFFFF00) == 0xFFFFFF00)
	    return pixel;

	int r = (pixel & 0xFF000000) >>> 24;
	int g = (pixel & 0x00FF0000) >>> 16;
	int b = (pixel & 0x0000FF00) >>> 8;
	int a = pixel & 0x000000FF;
	uncovered -= a;

	r = burnColorPart(r);
	g = burnColorPart(g);
	b = burnColorPart(b);
	a = Math.min(255, Math.round(a + (heat > SHOW_THRESHOLD ? SHOW_STEP : 0) * Gdx.graphics.getDeltaTime()));
	uncovered += a;

	r <<= 24;
	g <<= 16;
	b <<= 8;

	int result = r | g | b | a;
	burned += ((0xFFFFFFFF & result) == 0xFF) ? 1 : 0;
	return result;
    }

    private void controlState() {
	if (uncovered >= UNCOVER_REQ) {
	    System.out.print("solved");
	    GameState.paper_clearing = true;
	    clearing = CLEAR_TIME;
	    finished_paper = new Texture(Gdx.files.internal(Sources.getMaskName(GameState.current_paper)));
	    if (GameState.play_sound)
		GameState.succ.play(Constants.SUCC_VOLUME);
	} else if (burned >= BURN_REQ) {
	    uncovered = 0;
	    System.out.print("burned");
	    GameState.failed_clear_play = true;
	    GameState.paper_burning = true;
	    burning = BURN_TIME;
	    burned_paper = new Texture(Gdx.files.internal(Sources.BURNED_PAPER));
	    if (GameState.play_sound)
		GameState.burn.play(Constants.BURN_VOLUME);
	    
	}
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	// Change the heat based on the latest movement
	heat = Math.min(heat + (Gdx.graphics.getDeltaTime() * HEAT_INCREASE), HEAT_MAX);
	float distance = (float) Math.sqrt((last_x - mouse_x) * (last_x - mouse_x) + (last_y - mouse_y) * (last_y - mouse_y));
	heat = Math.max(0f, heat - distance * MOVE_DECREASE * Gdx.graphics.getDeltaTime());
	if (distance > MAX_MOVE_DISTANCE * Gdx.graphics.getDeltaTime())
	    heat = 0;

	// Update the pixels based on the last position and their diameter from
	// there
	burned = 0;
	for (int x = Math.max(0, mouse_x - DIAMETER / 2); x < Math.min(Constants.GAME_WIDTH, mouse_x + DIAMETER / 2); x++) {
	    int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouse_x) * (x - mouse_x))));
	    for (int y = Math.max(0, mouse_y - span); y < Math.min(Constants.GAME_HEIGHT, mouse_y + span); y++) {
		double prob = ((DIAMETER / 2) - Math.sqrt((last_x - x) * (last_x - x) + (last_y - y) * (last_y - y))) / JITTER;
		// On the borders draw only some
		if (prob > 1f || prob > MathUtils.random()) {
		    int pixel = morphPixel(current_mask_pix_.getPixel(x, y));
		    current_mask_pix_.drawPixel(x, y, pixel);
		}
	    }
	}
	current_mask_.draw(current_mask_pix_, 0, 0);

	controlState();
	last_x = mouse_x;
	last_y = mouse_y;
    }

    @Override
    public void update() {
	heat = Math.max(heat - (Gdx.graphics.getDeltaTime() * HEAT_DECREASE), 0);
	if (burning > 0) {
	    burning -= Gdx.graphics.getDeltaTime();
	} else if (GameState.paper_burning) {
	    GameState.paper_burning = false;
	    GameState.paper_burned = true;
	}
	if (clearing > 0) {
	    clearing -= Gdx.graphics.getDeltaTime();
	} else if (GameState.paper_clearing) {
	    GameState.paper_clearing = false;
	    GameState.paper_cleared = true;
	}
    }

    @Override
    public void draw(SpriteBatch batch) {
	super.draw(batch);
	// Overlay in the case of failure
	if (GameState.paper_burning || GameState.paper_burned) {
	    Color color = batch.getColor();
	    float old_a = color.a;
	    color.a = (1f / BURN_TIME) * (BURN_TIME - Math.max(burning, 0f));
	    batch.setColor(color);
	    batch.draw(burned_paper, 0, 0);
	    color.a = old_a;
	    batch.setColor(color);
	}
	// Overlay in the case of success
	if (GameState.paper_clearing || GameState.paper_cleared) {
	    Color color = batch.getColor();
	    float old_a = color.a;
	    color.a = (1f / CLEAR_TIME) * (CLEAR_TIME - Math.max(clearing, 0f));
	    batch.setColor(color);
	    batch.draw(finished_paper, 0, 0);
	    color.a = old_a;
	    batch.setColor(color);
	}
    }

    @Override
    public void drawHeat(OrthographicCamera cam) {
	// Draw warning circle if being heated.
	if (GameState.mouse_pressed) {
	    renderer.setProjectionMatrix(cam.combined);
	    renderer.begin(ShapeType.Filled);
	    float factor = Math.max(0, heat - SHOW_THRESHOLD) + Math.max(0, heat - BURN_THRESHOLD);
	    renderer.setColor(HEAT_R * factor, HEAT_G * factor, HEAT_B * factor, (float) HEAT_A * factor / HEAT_LAYERS);
	    for (int i = 0; i < HEAT_LAYERS; i++)
		renderer.circle(last_x, Constants.GAME_HEIGHT - last_y, (DIAMETER / 2f) * (1 + HEAT_SIZE * i));
	    renderer.end();
	}
    }

    @Override
    public void dispose() {
	super.dispose();
	renderer.dispose();
	if (burned_paper != null)
	    burned_paper.dispose();
	if (finished_paper != null)
	    finished_paper.dispose();
    }
}
