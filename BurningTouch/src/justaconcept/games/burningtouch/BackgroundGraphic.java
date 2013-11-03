package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is responsible for drawing a background object that fills the space behind the board.
 */
public class BackgroundGraphic implements SceneObject {
    // The images that constitute the board.
    final Texture border_piece_;
    final Texture corner_piece_;
    final Texture inner_piece_;

    // The coordinates of the board itself - topleft corner and number of pieces
    // from there in both directions.
    final int l_corner_X;
    final int rep_X;
    final int l_corner_Y;
    final int rep_Y;

    BackgroundGraphic() {
	border_piece_ = new Texture(Gdx.files.internal(Sources.BORDER_PIECE));
	corner_piece_ = new Texture(Gdx.files.internal(Sources.CORNER_PIECE));
	inner_piece_ = new Texture(Gdx.files.internal(Sources.INNER_PIECE));

	// Compute positions of where the pieces are to be displayed
	l_corner_X = (Gdx.graphics.getWidth() % Constants.PIECE_SIZE) / 2;
	rep_X = Gdx.graphics.getWidth() / Constants.PIECE_SIZE;
	l_corner_Y = (Gdx.graphics.getHeight()  % Constants.PIECE_SIZE) / 2;
	rep_Y = Gdx.graphics.getWidth() / Constants.PIECE_SIZE;
    }

    /**
     * Start with the corner, then move along the line and turn in the next
     * corner
     */
    void makeLine(final int reps) {
	/*p_.image(corner_piece_, 0f, 0f);
	for (int i = 0; i < reps; i++) {
	    p_.translate(Constants.PIECE_SIZE, 0f);
	    if (i < reps - 2) // Two pieces are not drawn - they are the corners
		p_.image(border_piece_, 0f, 0f);
	}
	p_.rotate(p_.PI / 2);*/
    }

    @Override
    public void draw(SpriteBatch batch) {
	/*// Draw the borders
	for (int i = 0; i < 4; i++)
	    makeLine(i % 2 == 1 ? rep_Y : rep_X);

	// Draw the inner pieces
	for (int X = 1; X < (rep_X - 1); X++)
	    for (int Y = 1; Y < (rep_Y - 1); Y++)
		p_.image(inner_piece_, X * Constants.PIECE_SIZE, Y * Constants.PIECE_SIZE);*/
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {
	// TODO Auto-generated method stub
	
    }
}
