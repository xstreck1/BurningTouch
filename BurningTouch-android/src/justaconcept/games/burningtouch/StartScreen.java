package justaconcept.games.burningtouch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StartScreen extends Activity {
    SharedPreferences prefs;
    SharedPreferences.Editor edit;
    private final String CURRENT_PAPER_SAVE = "current_paper";
    private final String LATEST_PAPER_SAVE = "latest_paper";
    private final String FAILED_PAPER_SAVE = "failed_paper";
    private final String PLAY_SOUND = "play_sound";
    private final String VIBRATE = "vibrate";
    
    void loadState() {
	GameState.current_paper = prefs.getInt(CURRENT_PAPER_SAVE, 1);
	GameState.latest_paper = prefs.getInt(LATEST_PAPER_SAVE, 1);
	GameState.failed_clear_play = prefs.getBoolean(FAILED_PAPER_SAVE, false);
	GameState.play_sound = prefs.getBoolean(PLAY_SOUND, true);
	GameState.vibrate = prefs.getBoolean(VIBRATE, true);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.start_screen);
	
	prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	edit = prefs.edit();
	loadState();	 
    }
    
    @Override
    protected void onResume() {
	super.onResume();
	((TextView) findViewById(R.id.button1)).setTextColor(getResources().getColor(R.color.button_font));
	((ToggleButton) findViewById(R.id.togglebutton1)).setChecked(GameState.play_sound);
	((ToggleButton) findViewById(R.id.togglebutton2)).setChecked(GameState.vibrate);
    }
    
    public void startNewGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
	
	Intent new_game_intent = new Intent(this, MainActivity.class);
	startActivity(new_game_intent);
    }
    
    public void playGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
	Intent new_game_intent = new Intent(this, MainActivity.class);
	startActivity(new_game_intent);
    }
    
    public void onSoundClicked(View view) {
	GameState.play_sound = ((ToggleButton) view).isChecked();
	if (GameState.play_sound)
	    ((ToggleButton) view).setText(R.string.sound_toggle_on);
	else 
	    ((ToggleButton) view).setText(R.string.sound_toggle_off);
	edit.putBoolean(PLAY_SOUND, GameState.play_sound);
	edit.apply();
    }
    
    public void onVibrateClicked(View view) {
	GameState.vibrate = ((ToggleButton) view).isChecked();
	if (GameState.vibrate)
	    ((ToggleButton) view).setText(R.string.vibrate_toggle_on);
	else 
	    ((ToggleButton) view).setText(R.string.vibrate_toggle_off);
	edit.putBoolean(VIBRATE, GameState.vibrate);
	edit.apply();
    }
    
    
    public void finishGame(View view) {
	((TextView) view).setTextColor(getResources().getColor(R.color.button_active));
	finish();
    }
    
    @Override
    protected void onPause() {
	super.onPause();
	edit.putInt(CURRENT_PAPER_SAVE, GameState.current_paper);
	edit.putInt(LATEST_PAPER_SAVE, GameState.latest_paper);
	edit.putBoolean(FAILED_PAPER_SAVE, GameState.failed_clear_play);
	edit.putBoolean(PLAY_SOUND, GameState.play_sound);
	edit.putBoolean(VIBRATE, GameState.vibrate);
	edit.apply();
    }
}
