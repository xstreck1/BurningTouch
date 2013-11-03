package justaconcept.HiddenStory.platform;

import justaconcept.HiddenStory.main.GameState;
import android.app.AlertDialog;
import android.content.Context;

public class Platform {
    static int SCREEN_WIDTH = 800;
    static int SCREEN_HEIGHT = 600;
    static public Context context;
    
    static public int getScreenWidth() {
	return SCREEN_WIDTH;
    }
    
    static public int getScreenHeight() {
	return SCREEN_HEIGHT;
    }
    
    static public void raiseError(String message) {
	AlertDialog.Builder builder = new AlertDialog.Builder(context);
	System.exit(0);
    }
    
    static public void loadGameState() {
	GameState.current_paper = 1;
	GameState.latest_paper = 2;
	GameState.burned = false;
    }
    
    static public void saveGameState() {
	
    }
}
