package justaconcept.games.burningtouch;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    static public void loadGameState() {
	GameState.current_paper = 1;
	GameState.latest_paper = 2;
	GameState.burned = false;
    }
    
    
    public static void main(String[] args) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = "BurningTouch";
	cfg.useGL20 = true;
	cfg.width = 800;
	cfg.height = 600;

	loadGameState();
	new LwjglApplication(new BurningTouch(), cfg);
    }
}
