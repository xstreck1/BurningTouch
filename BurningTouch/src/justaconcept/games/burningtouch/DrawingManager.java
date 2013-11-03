package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawingManager extends ObjectManager {
    private SpriteBatch batch;

    DrawingManager(HashMap<String, SceneObject> scene_objects_) {
	super(scene_objects_);
	batch = new SpriteBatch();
    }

    @Override
    void update() {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	batch.begin();
	scene_objects_.get(Constants.BG_OBJ_STR).draw(batch);
	scene_objects_.get(Constants.PPR_OBJ_STR).draw(batch);
	scene_objects_.get(Constants.BTN_OBJ_STR).draw(batch);

	batch.end();

	GameState.draw_what = GameState.DrawWhat.nothing;
    }
}
