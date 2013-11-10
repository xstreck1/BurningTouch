package justaconcept.games.burningtouch;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Pixmap;

public class BurningTouch implements ApplicationListener {
    private DrawingManager drawing_manager;
    private UpdateManager update_manager;
    private TouchManager touch_manager;
    private HashMap<String, SceneObject> scene_objects;
    private FPSLogger logger;

    @Override
    public void create() {
	LayoutManager.testResolution();

	UpdateManager.loadWorkingMask();

	scene_objects = new HashMap<String, SceneObject>();
	scene_objects.put(Constants.BG_OBJ_STR, new BackgroundGraphic());
	BasicPaper current_paper;
	if (GameState.current_paper == GameState.latest_paper)
	    current_paper = new DynamicPaper( GameState.working_mask);
	else
	    current_paper = new StaticPaper( GameState.current_paper);
	scene_objects.put(Constants.PPR_OBJ_STR, current_paper);
	scene_objects.put(Constants.BTN_OBJ_STR, new Buttons());

	drawing_manager = new DrawingManager( scene_objects);
	update_manager = new UpdateManager( scene_objects);
	touch_manager = new TouchManager( scene_objects);
	
	logger = new FPSLogger();
    }

    @Override
    public void resize(int width, int height) {
	// TODO Auto-generated method stub

    }

    @Override
    public void render() {
	touch_manager.update();
	update_manager.update();
	drawing_manager.update();
	logger.log();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
	if (GameState.current_paper == GameState.latest_paper)
	    ((DynamicPaper) scene_objects.get(Constants.PPR_OBJ_STR)).resume();
	    
    }

    @Override
    public void dispose() {
	
    }
}
