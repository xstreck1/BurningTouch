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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.start_screen);
	
	edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
    }
    
    
    @Override
    protected void onResume() {
	super.onResume();
	((TextView) findViewById(R.id.button1)).setTextColor(getResources().getColor(R.color.button_font));
	onToggleClicked(findViewById(R.id.togglebutton));
    }
    
    public void startNewGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new BurningTouch(), cfg);
    }
    
    public void continueGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new BurningTouch(), cfg);
    }
    
    public void onToggleClicked(View view) {
	boolean sound = ((ToggleButton) view).isChecked();
	edit.putBoolean("sound", sound);
	edit.apply();
    }
    
    public void finishGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
	finish();
    }
}