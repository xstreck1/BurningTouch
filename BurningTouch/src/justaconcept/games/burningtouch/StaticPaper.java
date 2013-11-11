package justaconcept.games.burningtouch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StaticPaper extends BasicPaper {  
    StaticPaper(int paper_no) {
	super();
	if (paper_no == Constants.PAPER_COUNT) {
	    this.current_mask_ = GameState.failed_clear_play ? new Texture(Gdx.files.internal(Sources.MASK_LAST_FAIL)) : new Texture(Gdx.files.internal(Sources.MASK_LAST_SUCC)) ;
	} else {
	    this.current_mask_ = new Texture(Gdx.files.internal(Sources.getMaskName(paper_no)));
	}
    }

    @Override
    public void update() {
	
    }

    @Override
    public void touch(int mouse_x, int mouse_y) {	
    }
}
