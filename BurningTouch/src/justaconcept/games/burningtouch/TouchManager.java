package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class TouchManager extends ObjectManager { 
    TouchManager (HashMap<String, SceneObject> scene_objects_) {
	super(scene_objects_);
    }
    
    @Override 
    void update()  {
	
	if (Gdx.input.isTouched() && (!GameState.paper_burning) && (!GameState.paper_clearing)) {
	    // System.out.println("MouseX: " + Gdx.input.getX() + ", MouseY: " + Gdx.input.getY());
	    
	    GameState.mouse_x = Math.round(Gdx.input.getX() * Gdx.graphics.getDensity()) - LayoutManager.getGame_X();
	    GameState.mouse_y = Math.round(Gdx.input.getY() * Gdx.graphics.getDensity()) - LayoutManager.getGame_Y();
	    
	    // Call a button if there is a click (not hold)
	    if (!GameState.mouse_pressed) {
		scene_objects_.get(Constants.BTN_OBJ_STR).touch(GameState.mouse_x, GameState.mouse_y);
	    }
	    // Invoke touching if buttons is not present.
	    if ((GameState.control == GameState.Control.none) && !GameState.paper_burned && !GameState.paper_cleared) {
		scene_objects_.get(Constants.PPR_OBJ_STR).touch(GameState.mouse_x, GameState.mouse_y);
	    }
	    GameState.mouse_pressed = true;
	} else {
	    GameState.mouse_x = -1;
	    GameState.mouse_y = -1;	
	    GameState.mouse_pressed = false;
	}
	
	
    }
}