package justaconcept.HiddenStory.main;
import java.util.HashMap;

import justaconcept.HiddenStory.platform.Platform;
import processing.core.PApplet;
import processing.core.PImage;

public class HiddenStory extends PApplet {
    private TimeManager time_manager;
    private DrawingManager drawing_manager;
    private UpdateManager update_manager;
    private TouchManager touch_manager;
    private HashMap<String, SceneObject> scene_objects;
    
    public void loadWorkingMask() {
	PImage source = loadImage(Sources.getMaskName(GameState.latest_paper, this));
	source.loadPixels();
	PImage mask = createImage(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, ARGB);
	mask.loadPixels();
	for (int i = 0; i < mask.pixels.length; i++) {
	    mask.pixels[i] = PerPixelFunc.makeInvis(this, source.pixels[i]);
	}
	mask.updatePixels();
	GameState.working_mask = mask;
    }

    public void setup() {
	LayoutManager.testResolution();
	
	frameRate(Constants.FRAMERATE);
	orientation(LANDSCAPE);
	size(Platform.getScreenWidth(), Platform.getScreenHeight());
	
	Platform.loadGameState();
	loadWorkingMask();
	
	time_manager = new TimeManager(this, Constants.FRAMERATE);
		
	scene_objects = new HashMap<String, SceneObject>();
	scene_objects.put(Constants.BG_OBJ_STR, new BackgroundGraphic(this));
	BasicPaper current_paper;
	if (GameState.current_paper == GameState.latest_paper) 
	    current_paper = new DynamicPaper(this, GameState.working_mask);
	else 
    	    current_paper = new StaticPaper(this, GameState.current_paper);
	scene_objects.put(Constants.PPR_OBJ_STR, current_paper);
	scene_objects.put(Constants.BTN_OBJ_STR, new Buttons(this));
	
	drawing_manager = new DrawingManager(this, scene_objects);
	update_manager = new UpdateManager(this, scene_objects);
	touch_manager = new TouchManager(this, scene_objects);
    }	

    public void draw() {
	time_manager.update();
	touch_manager.update(time_manager.getDelta_());
	update_manager.update(time_manager.getDelta_());
	drawing_manager.update(time_manager.getDelta_());
	// System.out.println("DeltaTime " + parseInt(time_manager.getDelta_()));
    }
    
    @Override
    public void stop() {
	super.destroy();
	 System.out.println("On stop");
	// mask.save("papyrus_covered.png");
    }
    
    @Override
    public void destroy() {
	super.stop();
	 System.out.println("On destroy");
	// mask.save("papyrus_covered.png");
    }

    public int sketchWidth() {
        return displayWidth;
    }

    public int sketchHeight() {
        return displayHeight;
    }
}
