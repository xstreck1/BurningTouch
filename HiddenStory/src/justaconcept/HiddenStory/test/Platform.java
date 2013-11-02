package justaconcept.HiddenStory.test;

import javax.swing.JOptionPane;

import justaconcept.HiddenStory.main.GameState;
import processing.core.PApplet;

public class Platform {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    
    static public int getScreenWidth() {
	return SCREEN_WIDTH;
    }
    
    static public int getScreenHeight() {
	return SCREEN_HEIGHT;
    }
    
    static public void raiseError(String message) {
	JOptionPane.showMessageDialog(null, message);
	System.exit(0);
    }
    
    static public void loadGameState(final PApplet p_) {
	GameState.current_paper = 1;
	GameState.latest_paper = 1;
	GameState.burned = false;
// 	GameState.working_mask = p_.loadImage(Sources.MASK_NAME + p_.toString(current_paper) + Sources.PICTURE_SUFFIX);
    }
    
    static public void saveGameState() {
	
    }
}
