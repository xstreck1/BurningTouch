package justaconcept.HiddenStory.main;

import processing.core.PApplet;

public class SceneObject {
    final PApplet p_;
    
    SceneObject(final PApplet p_) {
	this.p_ = p_;
    }
    
    void draw() {}
    
    void update() {}
    
    void touch(int mouse_x, int mouse_y) {}
}
