package justaconcept.games.burningtouch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    static private LwjglApplicationConfiguration loadGameState() {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = "BurningTouch";
	cfg.useGL20 = true;

	Properties prop = new Properties();
	try {
	    // load a properties file
	    prop.load(new FileInputStream("config.properties"));
	    // get the property value
	    cfg.width = Integer.parseInt(prop.getProperty("app_width"));
	    cfg.height = Integer.parseInt(prop.getProperty("app_height"));
	    Constants.PAPER_COUNT = Integer.parseInt(prop.getProperty("paper_count"));
	    GameState.current_paper = Integer.parseInt(prop.getProperty("current_paper"));
	    GameState.latest_paper = Integer.parseInt(prop.getProperty("latest_paper"));
	    GameState.failed_clear_play = Boolean.parseBoolean(prop.getProperty("failed_clear_play"));
	    Constants.CLEAR_TRHS = Float.parseFloat(prop.getProperty("clear_thrs"));
	    Constants.BURN_TRHS = Float.parseFloat(prop.getProperty("burn_thrs"));
	    Constants.BG_VOLUME = Float.parseFloat(prop.getProperty("bg_volume"));
	    Constants.BURN_VOLUME = Float.parseFloat(prop.getProperty("burn_volume"));
	    Constants.SUCC_VOLUME = Float.parseFloat(prop.getProperty("succ_volume"));
	} catch (IOException ex) {
	    ex.printStackTrace();
	}

	return cfg;
    }

    public static void main(String[] args) {
	new LwjglApplication(new BurningTouch(), loadGameState());
    }
}
