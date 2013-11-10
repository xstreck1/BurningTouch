package justaconcept.games.burningtouch;


public class Sources {
    final static String PICTURES_SUFFIX = ".png";
    
    final static String INNER_PIECE = "inner_piece.png";
    final static String CORNER_PIECE = "corner_piece.png";
    final static String BORDER_PIECE = "border_piece.png";
    
    final static String CLEAR_PAPER = "clear_paper.png";
    final static String BURNED_PAPER = "burned_paper.png";
    
    final static String MASK_NAME = "mask";
    final static String MASK_SUFFIX = ".png";
    
    static String getMaskName(final int paper_no) {
	return Sources.MASK_NAME + "_" + String.valueOf(paper_no) + Sources.MASK_SUFFIX;
    }
    
    final static String FORWARD_BUTTON = "forward_button.png";
    final static String BACKWARD_BUTTON = "backward_button.png";
}
