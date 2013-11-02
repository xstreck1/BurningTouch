package justaconcept.HiddenStory.main;

import processing.core.PApplet;
import processing.core.PImage;

public class BasicPaper extends SceneObject {
    PImage clear_paper_;
    PImage current_mask_;
    
    BasicPaper(PApplet p_) {
	super(p_);
    }

    @Override
    public void draw() {
	p_.image(clear_paper_, 0f, 0f);
	p_.image(current_mask_, 0f, 0f);
	// TODO Auto-generated method stub
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	// TODO Auto-generated method stub
	
    }

}
