package justaconcept.games.burningtouch;

import java.util.HashMap;

public class UpdateManager extends ObjectManager {
    
    UpdateManager(HashMap<String, SceneObject> scene_objects_) {
	super(scene_objects_);
    }
    
    private void resolveControl() {
	if (GameState.control == GameState.Control.backward) {
	    if (GameState.current_paper > 1) {
		GameState.current_paper--;
		scene_objects_.remove(Constants.PPR_OBJ_STR);
		scene_objects_.put(Constants.PPR_OBJ_STR, new StaticPaper(GameState.current_paper));
	    }
	} else if (GameState.control == GameState.Control.forward) {
	    if (GameState.current_paper < GameState.latest_paper) {
		GameState.current_paper++;
		scene_objects_.remove(Constants.PPR_OBJ_STR);
	    } 
	    if (GameState.current_paper < GameState.latest_paper) {
		scene_objects_.put(Constants.PPR_OBJ_STR, new StaticPaper(GameState.current_paper));
	    } else if (GameState.current_paper == GameState.latest_paper){
		scene_objects_.put(Constants.PPR_OBJ_STR, new DynamicPaper(GameState.working_mask));
	    }
	} else if (GameState.control == GameState.Control.reset) {
	    
	}
	GameState.control = GameState.Control.none;
    }
    
    @Override void update()  {
	resolveControl();
	
	if (GameState.paper_solved)
	    System.out.println("Solved");
	
	for (SceneObject scene_object : scene_objects_.values()) 
	    scene_object.update();
	    
    }
}
