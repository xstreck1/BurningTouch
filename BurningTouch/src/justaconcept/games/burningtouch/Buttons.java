package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class is responsible for drawing a background object that fills the space behind the board.
 */
public class Buttons implements SceneObject {
    private final int BUTTON_DIM = 50;
    private final Rectangle BACKWARD_BTN_POS;
    private final Rectangle FORWARD_BTN_POS;
    private final Texture backward_button;
    private final Texture forward_button;
    
    Buttons() {
	super();
	BACKWARD_BTN_POS = new Rectangle(0,  320 - BUTTON_DIM, BUTTON_DIM, BUTTON_DIM);
	FORWARD_BTN_POS = new Rectangle(480 - BUTTON_DIM, 0, BUTTON_DIM, BUTTON_DIM);
	backward_button = new Texture(Gdx.files.internal(Sources.BACKWARD_BUTTON));
	forward_button = new Texture(Gdx.files.internal(Sources.FORWARD_BUTTON));
    }
    
    private GameState.Control getControlButton(int mouse_x, int mouse_y) {
	if (BACKWARD_BTN_POS.contains(mouse_x, mouse_y)) 
	    return GameState.Control.backward;
	if (FORWARD_BTN_POS.contains(mouse_x, mouse_y))
	    return GameState.Control.forward; 
	return GameState.Control.none;
    }   
    
    @Override
    public void touch(final int mouse_x, final int mouse_y) {
	GameState.control = getControlButton(mouse_x, mouse_y);
    }
    
    @Override
    public void draw(SpriteBatch batch) {
	batch.draw(backward_button, BACKWARD_BTN_POS.x, BACKWARD_BTN_POS.y);
	batch.draw(forward_button, FORWARD_BTN_POS.x, FORWARD_BTN_POS.y);
    }

    @Override
    public void update() { }
}
