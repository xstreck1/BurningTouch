package justaconcept.HiddenStory.main;

import java.util.HashMap;

import processing.core.PApplet;

public class ObjectManager {
    protected PApplet p_;
    protected HashMap<String, SceneObject> scene_objects_;

    ObjectManager(final PApplet p_, final HashMap<String, SceneObject> scene_objects_) {
	this.p_ = p_;
	this.scene_objects_ = scene_objects_;	
    }
    
    void update(int delta_time) {
	
    }
}
