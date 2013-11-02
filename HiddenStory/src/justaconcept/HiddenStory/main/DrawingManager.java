package justaconcept.HiddenStory.main;

import java.util.HashMap;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;

public class DrawingManager extends ObjectManager {
    public enum DrawWhat {
	all, scene, nothing
    };

    private final int bg_col;
    private DrawWhat draw_what;

    
    DrawingManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);

	draw_what = DrawWhat.all;
	bg_col = p_.color(0, 0, 0);
    }
    
    @Override void update(int delta_time)  {
	switch (draw_what) {
	case all:
	    p_.fill(bg_col);
	    p_.rect(0f, 0f, Platform.getScreenWidth(), Platform.getScreenHeight());
	    scene_objects_.get(Constants.BG_OBJ_STR).draw();
	    LayoutManager.translateToBoard(p_);
	    scene_objects_.get(Constants.PPR_OBJ_STR).draw();
	    p_.resetMatrix();
	case nothing:
	    break;
	case scene:
	    LayoutManager.translateToBoard(p_);
	    scene_objects_.get(Constants.PPR_OBJ_STR).draw();
	    p_.resetMatrix();;
	default:
	    break;
	}
	
	draw_what = DrawWhat.nothing;
    }
}
