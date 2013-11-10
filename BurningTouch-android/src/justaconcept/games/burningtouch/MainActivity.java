package justaconcept.games.burningtouch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    static public void loadGameState() {
	GameState.current_paper = 1;
	GameState.latest_paper = 2;
	GameState.failed_clear_play = false;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        loadGameState();
        
        initialize(new BurningTouch(), cfg);
    }
}