package justaconcept.HiddenStory.main;

import java.util.HashMap;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;

public class DrawingManager {
    public enum DrawWhat {
	all, scene, nothing
    };

    private final PApplet p_; // Processing
    private final int bg_col;
    private DrawWhat draw_what;
    private HashMap<String, SceneObject> scene_objects_;

    
    DrawingManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	this.p_ = p_;
	this.scene_objects_ = scene_objects_;
	
	draw_what = DrawWhat.all;
	bg_col = p_.color(0, 0, 0);
    }
    
    void draw() {
	switch (draw_what) {
	case all:
	    p_.fill(bg_col);
	    p_.rect(0f, 0f, Platform.getScreenWidth(), Platform.getScreenHeight());
	    scene_objects_.get(Constants.BG_OBJ_STR).draw();
	case nothing:
	    break;
	case scene:
	    break;
	default:
	    break;
	}
	
	draw_what = DrawWhat.nothing;
    }
}
