package justaconcept.games.burningtouch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface SceneObject {    
    void draw(SpriteBatch batch);
    
    void update();
    
    void touch(final int mouse_x, final int mouse_y);
    
    void dispose();
}
