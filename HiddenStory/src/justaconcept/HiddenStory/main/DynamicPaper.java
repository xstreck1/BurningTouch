package justaconcept.HiddenStory.main;

import processing.core.PApplet;
import processing.core.PImage;

public class DynamicPaper extends BasicPaper {
    int delay = 0;

    final int DIAMETER = 60;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);
    
    boolean has_changed = false;

    DynamicPaper(PApplet p_, PImage current_mask_) {
	super(p_);
	this.clear_paper_ = p_.loadImage(Sources.CLEAR_PAPER);
	this.current_mask_ = current_mask_;
    }
    
    @Override
    void redraw(int mouse_x, int mouse_y) {
	p_.image(clear_paper_.get(mouse_x - RADIUS, mouse_y - RADIUS, RADIUS * 2, RADIUS * 2), (float) mouse_x - RADIUS, (float) mouse_y - RADIUS);
	System.out.println("Draw " + p_.parseInt(mouse_x) + " " + p_.parseInt(mouse_y) + " " + p_.parseInt(RADIUS));
	p_.image(current_mask_.get(mouse_x - RADIUS, mouse_y - RADIUS, RADIUS * 2, RADIUS * 2), (float) mouse_x - RADIUS, (float) mouse_y - RADIUS);
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	System.out.println("Update " + p_.parseInt(mouse_x) + " " + p_.parseInt(mouse_y));
	current_mask_.loadPixels();
	for (int x = p_.max(0, mouse_x - DIAMETER / 2); x < p_.min(Constants.GAME_WIDTH, mouse_x + DIAMETER / 2); x++) {
	    int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouse_x) * (x - mouse_x))));
	    for (int y = p_.max(0, mouse_y - span); y < p_.min(Constants.GAME_HEIGHT, mouse_y + span); y++) {
		int position = y * Constants.GAME_WIDTH + x;
		current_mask_.pixels[position] = PerPixelFunc.incAlpha(p_, current_mask_.pixels[position], 3);
	    }
	}
	current_mask_.updatePixels();
    }
}
