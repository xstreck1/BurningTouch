package justaconcept.HiddenStory.main;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class TouchManager extends ObjectManager { 
    TouchManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);
    }
    
    @Override 
    void update(int delta_time)  {
	
	if (p_.mousePressed) {
	    GameState.mouse_x = p_.mouseX - LayoutManager.getGame_X();
	    GameState.mouse_y = p_.mouseY - LayoutManager.getGame_Y();
	    
	    // Call a button if there is a click (not hold)
	    if (!GameState.mouse_pressed && p_.mousePressed) {
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
	
	GameState.mouse_pressed = p_.mousePressed;
    }
}