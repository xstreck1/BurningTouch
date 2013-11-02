package justaconcept.HiddenStory.main;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class TouchManager extends ObjectManager {
    boolean is_pressed = false;
    
    TouchManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);
    }
    
    @Override 
    void update(int delta_time)  {
	
	if (p_.mousePressed) {
	    // Call a button if there is a click (not hold)
	    if (!is_pressed && p_.mousePressed) {
		scene_objects_.get(Constants.BTN_OBJ_STR).touch(p_.mouseX - LayoutManager.getGame_X(), p_.mouseY - LayoutManager.getGame_Y() );
	    }
	    // Invoke touching if buttons is not present.
	    if (GameState.control == GameState.Control.none) {
		scene_objects_.get(Constants.PPR_OBJ_STR).touch(p_.mouseX - LayoutManager.getGame_X(), p_.mouseY - LayoutManager.getGame_Y() );
	    }
	    
	}
	
	is_pressed = p_.mousePressed;
    }
}