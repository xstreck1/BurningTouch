package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class DynamicPaper extends BasicPaper {
    Pixmap current_mask_pix_;

    int delay = 0;

    final int DIAMETER = 60;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);

    boolean has_changed = false;

    private float heat = 0f;
    private final float HEAT_INCREASE = 3f;
    private final float HEAT_DECREASE = 2f;
    private final float SHOW_THRESHOLD = 0.4f;
    private final float SHOW_STEP = 0.03f;
    private final float BURN_THRESHOLD = 0.9f;
    private final float BURN_STEP = 0.02f;
    private final float HEAT_MAX = 2f;

    DynamicPaper(Pixmap current_mask_pix_) {
	super();
	this.current_mask_pix_ = current_mask_pix_;
	this.current_mask_ = new Texture(current_mask_pix_);
    }
    
    private float burnColor(float col) {
	return Math.max(0f, col - Math.max(0, heat - BURN_THRESHOLD) * BURN_STEP);
    }
    
    private int morphPixel(int pixel) {
	Color color = new Color(pixel);
	
	
	color.r = burnColor(color.r);
	color.g = burnColor(color.g);
	color.b = burnColor(color.b);
	color.a = Math.min(1f, color.a + Math.max(0, heat - SHOW_THRESHOLD) * SHOW_STEP);

	return Color.rgba8888(color);
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	heat = Math.min(heat + (Gdx.graphics.getDeltaTime() * HEAT_INCREASE), HEAT_MAX);

	current_mask_pix_.setBlending(Pixmap.Blending.None);
	for (int x = Math.max(0, mouse_x - DIAMETER / 2); x < Math.min(Constants.GAME_WIDTH, mouse_x + DIAMETER / 2); x++) {
	    int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouse_x) * (x - mouse_x))));
	    for (int y = Math.max(0, mouse_y - span); y < Math.min(Constants.GAME_HEIGHT, mouse_y + span); y++) {
		current_mask_pix_.drawPixel(x, y, morphPixel(current_mask_pix_.getPixel(x, y)));
	    }
	}
	current_mask_.draw(current_mask_pix_, 0, 0);
    }

    @Override
    public void update() {
	heat = Math.max(heat - (Gdx.graphics.getDeltaTime() * HEAT_DECREASE), 0);
    }
}
