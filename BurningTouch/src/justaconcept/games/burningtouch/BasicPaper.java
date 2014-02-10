package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BasicPaper implements SceneObject {
    Texture filler_;
    
    public BasicPaper() {
    }
    
    @Override
    public void draw(SpriteBatch batch) {
	batch.draw(filler_,0,0);
    }
    
    public void drawHeat(OrthographicCamera cam) {}
    
    public void vibrate() {}
    
    public void dispose() {
	filler_.dispose();
    }
}
