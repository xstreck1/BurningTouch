package justaconcept.games.burningtouch;

import java.util.HashMap;

public abstract class ObjectManager {
    protected HashMap<String, SceneObject> scene_objects_;

    ObjectManager(final HashMap<String, SceneObject> scene_objects_) {
	this.scene_objects_ = scene_objects_;	
    }
    
    void update() {}
}
