package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is responsible for drawing a background object that fills the
 * space behind the board.
 */
public class BackgroundGraphic implements SceneObject {
    // The images that constitute the board.
    final Texture t_border_piece_, r_border_piece_, b_border_piece_, l_border_piece_;
    final Texture tl_corner_piece_, tr_corner_piece_, bl_corner_piece_, br_corner_piece_;
    final Texture inner_piece_;

    // The coordinates of the board itself - topleft corner and number of pieces
    // from there in both directions.
    final int l_corner_X;
    final int rep_X;
    final int l_corner_Y;
    final int rep_Y;

    BackgroundGraphic() {
	t_border_piece_ = new Texture(Gdx.files.internal(Sources.T_BORDER_PIECE));
	r_border_piece_ = new Texture(Gdx.files.internal(Sources.R_BORDER_PIECE));
	b_border_piece_ = new Texture(Gdx.files.internal(Sources.B_BORDER_PIECE));
	l_border_piece_ = new Texture(Gdx.files.internal(Sources.L_BORDER_PIECE));
	tl_corner_piece_ = new Texture(Gdx.files.internal(Sources.TL_CORNER_PIECE));
	tr_corner_piece_ = new Texture(Gdx.files.internal(Sources.TR_CORNER_PIECE));
	bl_corner_piece_ = new Texture(Gdx.files.internal(Sources.BL_CORNER_PIECE));
	br_corner_piece_ = new Texture(Gdx.files.internal(Sources.BR_CORNER_PIECE));
	inner_piece_ = new Texture(Gdx.files.internal(Sources.INNER_PIECE));

	// Compute positions of where the pieces are to be displayed
	l_corner_X = (Gdx.graphics.getWidth() % Constants.PIECE_SIZE) / 2;
	rep_X = Gdx.graphics.getWidth() / Constants.PIECE_SIZE;
	l_corner_Y = (Gdx.graphics.getHeight() % Constants.PIECE_SIZE) / 2;
	rep_Y = Gdx.graphics.getHeight() / Constants.PIECE_SIZE;
    }

    /**
     * Display a piece rotated around it's origin.
     */
    void displayRotated(SpriteBatch batch, Texture piece, float x, float y, float rotation) {
	batch.draw(piece, x, y, Constants.PIECE_SIZE / 2f, Constants.PIECE_SIZE / 2f, (float) Constants.PIECE_SIZE, (float) Constants.PIECE_SIZE, 1f, 1f, rotation, 0, 0,
		Constants.PIECE_SIZE, Constants.PIECE_SIZE, false, false);
    }

    /**
     * Position and rotate the corners
     */
    void makeCorners(SpriteBatch batch) {
	batch.draw(bl_corner_piece_, -l_corner_X, l_corner_Y);
	batch.draw(tl_corner_piece_, -l_corner_X, Gdx.graphics.getHeight() - Constants.PIECE_SIZE - l_corner_Y);
	batch.draw(tr_corner_piece_, Gdx.graphics.getWidth() - Constants.PIECE_SIZE + l_corner_X, Gdx.graphics.getHeight() - Constants.PIECE_SIZE - l_corner_Y);
	batch.draw(br_corner_piece_, Gdx.graphics.getWidth() - Constants.PIECE_SIZE + l_corner_X, l_corner_Y);
    }

    /**
     * Position and rotate bordering pieces
     */
    void makeBorders(SpriteBatch batch) {
	for (int X = 1; X < (rep_X - 1); X++) {
	    batch.draw(b_border_piece_, -l_corner_X + X * Constants.PIECE_SIZE, l_corner_Y);
	    batch.draw(t_border_piece_, -l_corner_X + X * Constants.PIECE_SIZE, Gdx.graphics.getHeight() - Constants.PIECE_SIZE - l_corner_Y);
	}
	for (int Y = 1; Y < (rep_Y - 1); Y++) {
	    batch.draw(l_border_piece_, -l_corner_X, l_corner_Y  + Y * Constants.PIECE_SIZE);
	    batch.draw(r_border_piece_, l_corner_X + Gdx.graphics.getWidth() - Constants.PIECE_SIZE, Gdx.graphics.getHeight() - Constants.PIECE_SIZE - l_corner_Y  - Y * Constants.PIECE_SIZE);
	}
    }

    @Override
    public void draw(SpriteBatch batch) {
	makeCorners(batch);
	makeBorders(batch);

	// Draw the inner pieces
	for (int X = 1; X < (rep_X - 1); X++)
	    for (int Y = 1; Y < (rep_Y - 1); Y++)
		batch.draw(inner_piece_, X * Constants.PIECE_SIZE + l_corner_X, Y * Constants.PIECE_SIZE + l_corner_Y);
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
