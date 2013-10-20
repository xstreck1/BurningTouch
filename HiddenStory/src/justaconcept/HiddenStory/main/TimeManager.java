package justaconcept.HiddenStory.main;

import processing.core.PApplet;

public class TimeManager {
    private final PApplet p_; // Processing
    private final int framerate_;
    private final int start_time_;
    private int last_call_;
    private int delta_;
    
    public TimeManager(PApplet p, int framerate) {
	p_ = p;
	framerate_ = framerate;
	start_time_ = p_.millis();
	last_call_ = start_time_;
    }
    
    public void update() {
	int current_time = p_.millis();
	delta_ = current_time - last_call_;
	last_call_ = current_time; 
    }
    
    public int toTime(final int frames) {
	float secs = (float) frames / (float) framerate_;
	return p_.round(secs * 1000f);
    }
    
    public int toFrames(final int milliseconds) {
	float frames = milliseconds * framerate_ / 1000f;
	return p_.round(frames);
    }
    
    public int getDelta_() {
	return delta_;
    }
    
    public int getFramerate_() {
        return framerate_;
    }

    public int getStart_time_() {
        return start_time_;
    }

}
