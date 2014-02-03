package justaconcept.games.burningtouch;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;

public class GameState {
    static public boolean play_sound = true;

    static public int current_paper = 1; // Numerical id of the currently used
					 // paper
    static public int latest_paper = 1; // Numerical id of the paper the player
					// is currently solving
    static public boolean failed_clear_play = false; // True after the player
						     // burns a paper

    static public Pixmap working_mask = null; // The text currently being
					      // elucidated
    static public Sound burn = null;
    static public Sound succ = null;
    static public Music background_music = null;

    public enum Control {
	forward, backward, reset, none;
    }

    static Control control = Control.none;
    static int mouse_x = -1;
    static int mouse_y = -1;
    static boolean mouse_pressed = false;

    static boolean paper_burning = false;
    static boolean paper_burned = false;
    static boolean paper_clearing = false;
    static boolean paper_cleared = false;
    static boolean paper_blocked = false;
    static boolean buttons_blocked = false;

    static void reset() {
	control = Control.none;
	mouse_x = -1;
	mouse_y = -1;
	mouse_pressed = false;

	paper_burning = false;
	paper_burned = false;
	paper_clearing = false;
	paper_cleared = false;
	paper_blocked = false;
	buttons_blocked = false;
    }
}
