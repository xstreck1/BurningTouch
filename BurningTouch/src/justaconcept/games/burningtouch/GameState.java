package justaconcept.games.burningtouch;

import com.badlogic.gdx.graphics.Pixmap;

public class GameState {
    static public int current_paper = 1; // Numerical id of the currently used paper 
    static public int latest_paper = 1; // Numerical id of the paper the player is currently solving
    static public boolean failed_clear_play = false; // True after the player burns a paper
    
    static public Pixmap working_mask = null; // The text currently being elucidated 
    
    public enum Control {
	forward, backward, reset, none;
    }
    static public Control control = Control.none;
    static int mouse_x = -1;
    static int mouse_y = -1;
    static boolean mouse_pressed = false;
    
    
    static boolean paper_burning = false;
    static boolean paper_burned = false;
    static boolean paper_solved = false;
    static boolean paper_blocked = false;
    static boolean buttons_blocked = false;
}
