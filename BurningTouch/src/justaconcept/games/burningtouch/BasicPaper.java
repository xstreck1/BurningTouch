package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BasicPaper implements SceneObject {
    Texture clear_paper_;
    Texture current_mask_;
    
    public BasicPaper() {
	this.clear_paper_ = new Texture(Gdx.files.internal(Sources.CLEAR_PAPER));
    }
    
    @Override
    public void draw(SpriteBatch batch) {
	batch.draw(clear_paper_,0,0);
	batch.draw(current_mask_,0,0);
    }
    
    public void drawHeat(OrthographicCamera cam) {}
    
    public void dispose() {
	clear_paper_.dispose();
	current_mask_.dispose();
    }
}
