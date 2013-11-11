package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class is responsible for drawing a background object that fills the
 * space behind the board.
 */
public class Buttons implements SceneObject {
    private final int BUTTON_DIM = 50;
    private final Rectangle BACKWARD_BTN_POS;
    private final Rectangle FORWARD_BTN_POS;
    private final Rectangle RESET_BTN_POS;
    private final Texture backward_button;
    private final Texture forward_button;
    private final Texture reset_button;

    Buttons() {
	super();
	BACKWARD_BTN_POS = new Rectangle(0, Constants.GAME_HEIGHT - BUTTON_DIM, BUTTON_DIM, BUTTON_DIM);
	FORWARD_BTN_POS = new Rectangle(Constants.GAME_WIDTH - BUTTON_DIM, 0, BUTTON_DIM, BUTTON_DIM);
	RESET_BTN_POS = new Rectangle((Constants.GAME_WIDTH - BUTTON_DIM) / 2, (Constants.GAME_HEIGHT - BUTTON_DIM) / 2, BUTTON_DIM, BUTTON_DIM);
	backward_button = new Texture(Gdx.files.internal(Sources.BACKWARD_BUTTON));
	forward_button = new Texture(Gdx.files.internal(Sources.FORWARD_BUTTON));
	reset_button = new Texture(Gdx.files.internal(Sources.RESET_BUTTON));
    }

    private GameState.Control getControlButton(int mouse_x, int mouse_y) {
	if (!GameState.paper_burned && BACKWARD_BTN_POS.contains(mouse_x, mouse_y) && GameState.current_paper != 1)
	    return GameState.Control.backward;
	if (!GameState.paper_burned && FORWARD_BTN_POS.contains(mouse_x, mouse_y) && GameState.current_paper != GameState.latest_paper)
	    return GameState.Control.forward;
	if (GameState.paper_burned && RESET_BTN_POS.contains(mouse_x, mouse_y))
	    return GameState.Control.reset;
	return GameState.Control.none;
    }

    @Override
    public void touch(final int mouse_x, final int mouse_y) {
	GameState.control = getControlButton(mouse_x, Constants.GAME_HEIGHT - mouse_y);
    }

    @Override
    public void draw(SpriteBatch batch) {
	if (GameState.paper_burned)
	    batch.draw(reset_button, RESET_BTN_POS.x, RESET_BTN_POS.y);
	else {
	    if (GameState.current_paper != 1)
		batch.draw(backward_button, BACKWARD_BTN_POS.x, BACKWARD_BTN_POS.y);
	    if (GameState.current_paper != GameState.latest_paper)
		batch.draw(forward_button, FORWARD_BTN_POS.x, FORWARD_BTN_POS.y);
	}
    }

    @Override
    public void update() {
    }
}
