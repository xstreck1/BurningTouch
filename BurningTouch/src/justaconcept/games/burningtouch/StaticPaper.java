package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StaticPaper extends BasicPaper {  
    StaticPaper(int paper_no) {
	super();
	this.current_mask_ = new Texture(Gdx.files.internal(Sources.getMaskName(paper_no)));
    }

    @Override
    public void update() {
	
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {	
    }
}
