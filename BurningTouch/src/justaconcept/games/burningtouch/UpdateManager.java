package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class UpdateManager extends ObjectManager {
    
    public static void loadWorkingMask() {
	if (GameState.working_mask != null)
	    GameState.working_mask.dispose();
	GameState.working_mask = new Pixmap(Gdx.files.internal(Sources.getMaskName(GameState.latest_paper)));

	GameState.working_mask.setBlending(Pixmap.Blending.None);
	for (int x = 0; x < Constants.GAME_WIDTH; x++) {
	    for (int y = 0; y < Constants.GAME_HEIGHT; y++) {
		GameState.working_mask.drawPixel(x, y, GameState.working_mask.getPixel(x, y) & 0xFFFFFF00);
	    }
	}
    }
    
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
	    GameState.paper_burned = false;
	    loadWorkingMask();
	    scene_objects_.put(Constants.PPR_OBJ_STR, new DynamicPaper(GameState.working_mask));
	}
	GameState.control = GameState.Control.none;
    }
    
    @Override void update()  {
	resolveControl();
	
	if (GameState.paper_cleared) {
	    GameState.paper_cleared = false;
	    scene_objects_.put(Constants.PPR_OBJ_STR, new StaticPaper(GameState.current_paper));
	    GameState.latest_paper = Math.min(GameState.latest_paper + 1, Constants.PAPER_COUNT);
	    loadWorkingMask();
	}
	
	for (SceneObject scene_object : scene_objects_.values()) 
	    scene_object.update();
	    
    }
}
