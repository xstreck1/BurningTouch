package justaconcept.HiddenStory.main;

import java.util.HashMap;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;

public class TouchManager extends ObjectManager {
    boolean is_pressed = false;
    
    TouchManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);
    }
    
    
    private GameState.Control getControlButton() {
	if (Constants.BACKWARD_BTN_POS.holdsPoint(p_.mouseX, p_.mouseY)) 
	    return GameState.Control.backward;
	else if (Constants.FORWARD_BTN_POS.holdsPoint(p_.mouseX, p_.mouseY))
	    return GameState.Control.forward;
	else if (Constants.RESET_BTN_POS.holdsPoint(p_.mouseX, p_.mouseY))
	    return GameState.Control.reset;
	else 
	    return GameState.Control.none;
    }
    
    @Override 
    void update(int delta_time)  {
	
	if (p_.mousePressed) {
	    GameState.Control control_called = GameState.Control.none;
	    // Call a button if there is a click (not hold)
	    if (!is_pressed && p_.mousePressed) {
		control_called = getControlButton();
	    }
	    // Call an event if touch.
	    if (control_called == GameState.Control.none) {
		
	    }
	    
	}
	
	is_pressed = p_.mousePressed;
    }
    
    
}