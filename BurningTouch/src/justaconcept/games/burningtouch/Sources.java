package justaconcept.games.burningtouch;


public class Sources {
    final static String PICTURES_SUFFIX = ".png";
    
    final static String INNER_PIECE = "inner_piece.png";
    final static String BL_CORNER_PIECE = "bl_corner_piece.png";
    final static String BR_CORNER_PIECE = "br_corner_piece.png";
    final static String TL_CORNER_PIECE = "tl_corner_piece.png";
    final static String TR_CORNER_PIECE = "tr_corner_piece.png";
    final static String T_BORDER_PIECE = "t_border_piece.png";
    final static String R_BORDER_PIECE = "r_border_piece.png";
    final static String B_BORDER_PIECE = "b_border_piece.png";
    final static String L_BORDER_PIECE = "l_border_piece.png";
    
    final static String CLEAR_PAPER = "clear_paper.png";
    final static String BURNED_PAPER = "burned_paper.png";
    
    final static String MASK_NAME = "mask";
    final static String MASK_SUFFIX = ".png";
    final static String MASK_LAST_FAIL = "final_fail.png";
    final static String MASK_LAST_SUCC = "final_succ.png";
    
    static String getMaskName(final int paper_no) {
	return Sources.MASK_NAME + "_" + String.valueOf(paper_no) + Sources.MASK_SUFFIX;
    }
    
    final static String FORWARD_BUTTON = "forward_button.png";
    final static String BACKWARD_BUTTON = "backward_button.png";
    final static String RESET_BUTTON = "reset_button.png";
}
