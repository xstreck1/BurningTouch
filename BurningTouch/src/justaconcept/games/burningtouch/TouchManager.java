package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class TouchManager extends ObjectManager { 
    TouchManager (HashMap<String, SceneObject> scene_objects_) {
	super(scene_objects_);
    }
    
    @Override 
    void update()  {
	
	if (Gdx.input.isTouched()) {
	    GameState.mouse_x = Gdx.input.getX() - LayoutManager.getGame_X();
	    GameState.mouse_y = Gdx.input.getY() - LayoutManager.getGame_Y();
	    
	    // Call a button if there is a click (not hold)
	    if (!GameState.mouse_pressed) {
		scene_objects_.get(Constants.BTN_OBJ_STR).touch(GameState.mouse_x, GameState.mouse_y);
		GameState.draw_what = GameState.DrawWhat.scene;
	    }
	    // Invoke touching if buttons is not present.
	    if (GameState.control == GameState.Control.none) {
		scene_objects_.get(Constants.PPR_OBJ_STR).touch(GameState.mouse_x, GameState.mouse_y);
		GameState.draw_what = GameState.DrawWhat.touch;
	    }
	} else {
	    GameState.mouse_x = -1;
	    GameState.mouse_y = -1;	    
	}
	
	GameState.mouse_pressed = Gdx.input.isTouched();
    }
}