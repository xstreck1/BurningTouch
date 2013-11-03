package justaconcept.games.burningtouch;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class DynamicPaper extends BasicPaper {
    Pixmap current_mask_pix_;
    
    int delay = 0;

    final int DIAMETER = 60;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);
    
    boolean has_changed = false;

    DynamicPaper(Pixmap current_mask_pix_) {
	super();
	this.current_mask_pix_ = current_mask_pix_;
	this.current_mask_ = new Texture(current_mask_pix_);
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	current_mask_pix_.setBlending(Pixmap.Blending.None);
	for (int x = Math.max(0, mouse_x - DIAMETER / 2); x < Math.min(Constants.GAME_WIDTH, mouse_x + DIAMETER / 2); x++) {
	    int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouse_x) * (x - mouse_x))));
	    for (int y = Math.max(0, mouse_y - span); y < Math.min(Constants.GAME_HEIGHT, mouse_y + span); y++) {
		current_mask_pix_.drawPixel(x, y, current_mask_pix_.getPixel(x, y) + 0x1);
	    }
	}
	current_mask_.draw(current_mask_pix_, 0, 0);
    }

    @Override
    public void update() { }
}
