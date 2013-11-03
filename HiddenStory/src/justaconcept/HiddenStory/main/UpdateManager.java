package justaconcept.HiddenStory.main;

import java.util.HashMap;

import processing.core.PApplet;

public class UpdateManager extends ObjectManager {
    
    UpdateManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);
    }
    
    @Override void update(int delta_time)  {
	if (GameState.control == GameState.Control.backward) {
	    if (GameState.current_paper > 1) {
		GameState.current_paper--;
		scene_objects_.remove(Constants.PPR_OBJ_STR);
		scene_objects_.put(Constants.PPR_OBJ_STR, new StaticPaper(p_, GameState.current_paper));
	    }
	} else if (GameState.control == GameState.Control.forward) {
	    if (GameState.current_paper < GameState.latest_paper) {
		GameState.current_paper++;
		scene_objects_.remove(Constants.PPR_OBJ_STR);
	    } 
	    if (GameState.current_paper < GameState.latest_paper) {
		scene_objects_.put(Constants.PPR_OBJ_STR, new StaticPaper(p_, GameState.current_paper));
	    } else {
		scene_objects_.put(Constants.PPR_OBJ_STR, new DynamicPaper(p_, GameState.working_mask));
	    }
	} else if (GameState.control == GameState.Control.reset) {
	    
	}
	GameState.control = GameState.Control.none;
    }
}
