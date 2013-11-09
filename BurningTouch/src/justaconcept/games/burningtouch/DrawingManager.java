package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DrawingManager extends ObjectManager {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Rectangle glViewport;

    DrawingManager(HashMap<String, SceneObject> scene_objects_) {
	super(scene_objects_);
	batch = new SpriteBatch();
	cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	resetCamera();
    }
    
    public void resetCamera() {
	cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
	cam.update();
	batch.setProjectionMatrix(cam.combined);
    }

    @Override
    void update() {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
        
	batch.begin();
	scene_objects_.get(Constants.BG_OBJ_STR).draw(batch);
	batch.end();
	
	batch.begin();
	cam.translate(-LayoutManager.getGame_X(), -LayoutManager.getGame_Y(), 0);
	cam.update();
	batch.setProjectionMatrix(cam.combined);
	
	scene_objects_.get(Constants.PPR_OBJ_STR).draw(batch);
	scene_objects_.get(Constants.BTN_OBJ_STR).draw(batch);
	batch.end();
	
	Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
	((BasicPaper) scene_objects_.get(Constants.PPR_OBJ_STR)).drawHeat(cam);
	
	resetCamera();
    }
}
