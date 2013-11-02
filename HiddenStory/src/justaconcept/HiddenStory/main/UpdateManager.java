package justaconcept.HiddenStory.main;

import java.util.HashMap;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;

public class UpdateManager extends ObjectManager {
    
    UpdateManager(PApplet p_, HashMap<String, SceneObject> scene_objects_) {
	super(p_, scene_objects_);
    }
    
    @Override void update(int delta_time)  {

    }
}
