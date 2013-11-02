package justaconcept.HiddenStory.main;

public class Constants {
    // Temporary values, used for the applet
    static final int GAME_WIDTH = 480; 
    static final int GAME_HEIGHT = 320;
     
    static final int FRAMERATE = 30;
    
    static final int PIECE_SIZE = 80; // Size of a single square of background in pixels.
    static final String BG_OBJ_STR = "background_object";
    static final String PPR_OBJ_STR = "paper_object";
    static final String BTN_OBJ_STR = "buttons_object";
    
    static final int SCENE_COUNT = 10;
    
    private static final int BUTTON_DIM = 50;
    static final Rectangle BACKWARD_BTN_POS = new Rectangle(0, 0, BUTTON_DIM, BUTTON_DIM);
    static final Rectangle FORWARD_BTN_POS = new Rectangle(480 - BUTTON_DIM, 320 - BUTTON_DIM, BUTTON_DIM, BUTTON_DIM);
    static final Rectangle RESET_BTN_POS = new Rectangle(480 - BUTTON_DIM, 0, BUTTON_DIM, BUTTON_DIM);
}