package justaconcept.HiddenStory.main;

import java.util.HashMap;

import justaconcept.HiddenStory.platform.Platform;
import processing.core.PApplet;

public class DrawingManager extends ObjectManager {
    private final int bg_col;
    

    
    DrawingManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);

	bg_col = p_.color(0, 0, 0);
    }
    
    @Override void update(int delta_time)  {
	switch (GameState.draw_what) {
	case all:
	    p_.fill(bg_col);
	    p_.rect(0f, 0f, Platform.getScreenWidth(), Platform.getScreenHeight());
	    scene_objects_.get(Constants.BG_OBJ_STR).draw();
	    LayoutManager.translateToBoard(p_);
	    scene_objects_.get(Constants.PPR_OBJ_STR).draw();
	    scene_objects_.get(Constants.BTN_OBJ_STR).draw();
	    p_.resetMatrix();
	    break;
	case nothing:
	    break;
	case scene:
	    LayoutManager.translateToBoard(p_);
	    scene_objects_.get(Constants.PPR_OBJ_STR).draw();
	    scene_objects_.get(Constants.BTN_OBJ_STR).draw();
	    p_.resetMatrix();
	    break;
	case touch:
	    LayoutManager.translateToBoard(p_);
	    ((BasicPaper) scene_objects_.get(Constants.PPR_OBJ_STR)).redraw(GameState.mouse_x, GameState.mouse_y);
	    scene_objects_.get(Constants.BTN_OBJ_STR).draw();
	    p_.resetMatrix();
	    break;
	default:
	    break;
	}
	
	GameState.draw_what = GameState.DrawWhat.nothing;
    }
}
