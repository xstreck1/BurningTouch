package justaconcept.HiddenStory.main;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is responsible for drawing a background object that fills the space behind the board.
 */
public class Buttons extends SceneObject {
    private final int BUTTON_DIM = 50;
    private final Rectangle BACKWARD_BTN_POS;
    private final Rectangle FORWARD_BTN_POS;
    private final PImage backward_button;
    private final PImage forward_button;
    
    Buttons(final PApplet p_) {
	super(p_);
	BACKWARD_BTN_POS = new Rectangle(0,  0, BUTTON_DIM, BUTTON_DIM);
	FORWARD_BTN_POS = new Rectangle(480 - BUTTON_DIM, 320 - BUTTON_DIM, BUTTON_DIM, BUTTON_DIM);
	backward_button = p_.loadImage(Sources.BACKWARD_BUTTON);
	forward_button = p_.loadImage(Sources.FORWARD_BUTTON);
    }
    
    private GameState.Control getControlButton(int mouse_x, int mouse_y) {
	if (BACKWARD_BTN_POS.holdsPoint(mouse_x, mouse_y)) 
	    return GameState.Control.backward;
	if (FORWARD_BTN_POS.holdsPoint(mouse_x, mouse_y))
	    return GameState.Control.forward; 
	return GameState.Control.none;
    }   
    
    @Override
    void touch(int mouse_x, int mouse_y) {
	GameState.control = getControlButton(mouse_x, mouse_y);
    }
    
    @Override
    void draw() {
	p_.image(backward_button, BACKWARD_BTN_POS.x_, BACKWARD_BTN_POS.y_);
	p_.image(forward_button, FORWARD_BTN_POS.x_, FORWARD_BTN_POS.y_);
    }
}
