package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StaticPaper extends BasicPaper {  
    StaticPaper(int paper_no) {
	super();
	filler_ = new Texture(Gdx.files.internal(Sources.getMaskName(paper_no)));
    }

    @Override
    public void update() {
	
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {	
    }
    
    @Override
    public void dispose() {
	super.dispose();
    }
}
