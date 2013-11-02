package justaconcept.HiddenStory.main;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class is responsible for drawing a background object that fills the space behind the board.
 */
public class BackgroundGraphic extends SceneObject {
    // The images that constitute the board.
    final PImage border_piece_;
    final PImage corner_piece_;
    final PImage inner_piece_;

    // The coordinates of the board itself - topleft corner and number of pieces
    // from there in both directions.
    final int l_corner_X;
    final int rep_X;
    final int l_corner_Y;
    final int rep_Y;

    BackgroundGraphic(final PApplet p_) {
	super(p_);

	border_piece_ = p_.loadImage(Sources.BORDER_PIECE);
	corner_piece_ = p_.loadImage(Sources.CORNER_PIECE);
	inner_piece_ = p_.loadImage(Sources.INNER_PIECE);

	// Compute positions of where the pieces are to be displayed
	l_corner_X = (Platform.getScreenWidth() % Constants.PIECE_SIZE) / 2;
	rep_X = Platform.getScreenWidth() / Constants.PIECE_SIZE;
	l_corner_Y = (Platform.getScreenHeight() % Constants.PIECE_SIZE) / 2;
	rep_Y = Platform.getScreenHeight() / Constants.PIECE_SIZE;
    }

    /**
     * Start with the corner, then move along the line and turn in the next
     * corner
     */
    void makeLine(final int reps) {
	p_.image(corner_piece_, 0f, 0f);
	for (int i = 0; i < reps; i++) {
	    p_.translate(Constants.PIECE_SIZE, 0f);
	    if (i < reps - 2) // Two pieces are not drawn - they are the corners
		p_.image(border_piece_, 0f, 0f);
	}
	p_.rotate(p_.PI / 2);
    }

    @Override
    public void draw() {
	p_.pushMatrix();
	p_.translate(l_corner_X, l_corner_Y);

	// Draw the borders
	for (int i = 0; i < 4; i++)
	    makeLine(i % 2 == 1 ? rep_Y : rep_X);

	// Draw the inner pieces
	for (int X = 1; X < (rep_X - 1); X++)
	    for (int Y = 1; Y < (rep_Y - 1); Y++)
		p_.image(inner_piece_, X * Constants.PIECE_SIZE, Y * Constants.PIECE_SIZE);

	p_.popMatrix();
    }
}
