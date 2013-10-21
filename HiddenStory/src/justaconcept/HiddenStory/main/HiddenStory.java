package justaconcept.HiddenStory.main;
import java.util.HashMap;

import justaconcept.HiddenStory.test.Platform;
import processing.core.PApplet;

public class HiddenStory extends PApplet {
    private TimeManager time_manager;
    private DrawingManager drawing_manager;
    private HashMap<String, SceneObject> scene_objects;
    
//    private PImage back;
//    private PImage front;
//    private PImage mask;
//    private PFont font;
//    
//    private final int SCENE_COUNT = 1;
//    private int current_scene = 1;
//    private int reached_scene = 1;
//    private Paper current_paper;

    public void setup() {
	frameRate(Constants.FRAMERATE);
	orientation(LANDSCAPE);
	size(Platform.getScreenWidth(), Platform.getScreenHeight());
	time_manager = new TimeManager(this, Constants.FRAMERATE);
		
	scene_objects = new HashMap<String, SceneObject>();
	scene_objects.put(Constants.BG_OBJ_STR, new BackgroundGraphic(this));
	
	drawing_manager = new DrawingManager(this, scene_objects);
    }	
	
//	font = createFont("Arial", 48);
//	back = loadImage("papyrus.png");
//	front = loadImage("papyrus_covered_base.png");
//	mask = createImage(GAME_WIDTH, GAME_HEIGHT, ARGB);
//	mask.loadPixels();
//	front.loadPixels();
//	for (int i = 0; i < front.pixels.length; i++) {
//	    int pix_color = front.pixels[i];
//	    int a = 0; // (pix_color >> 24) & 0xFF;
//	    int r = (pix_color >> 16) & 0xFF; // Faster way of getting red(argb)
//	    int g = (pix_color >> 8) & 0xFF; // Faster way of getting
//					     // green(argb)
//	    int b = pix_color & 0xFF;
//	    mask.pixels[i] = color(r, g, b, a);
//	}
//	mask.updatePixels();
//	background(back);
//	image(mask.get(0,0,480,320),0,0);
//	loop();
   

//    int delay = 0;
//
//    final int DIAMETER = 60;
//    final int RADIUS = DIAMETER / 2;
//    final int SQR_RAD = (RADIUS) * (RADIUS);

    public void draw() {
	drawing_manager.draw();
    }
	// Draw the stuff if necessary.
	
//	if (mousePressed) {
//	    mask.loadPixels();
//	    delay++;
//	    for (int x = max(0, mouseX - DIAMETER / 2); x < min(GAME_WIDTH, mouseX + DIAMETER / 2); x++) {
//		int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouseX) * (x - mouseX))));
//		for (int y = max(0, mouseY - span); y < min(GAME_HEIGHT, mouseY + span); y++) {
//		    int position = y * 480 + x;
//		    int pix_color = mask.pixels[position];
//		    int a = (pix_color >> 24) & 0xFF;
//		    int r = (pix_color >> 16) & 0xFF; // Faster way of getting
//						      // red(argb)
//		    int g = (pix_color >> 8) & 0xFF; // Faster way of getting
//						     // green(argb)
//		    int b = pix_color & 0xFF;
//		    if (a < 0xFF)
//			mask.pixels[position] = color(r, g, b, a + 5);
//		    if (delay > 10)
//			mask.pixels[position] = color(r - 2, g - 2, b - 5, a + 1);
//		}
//	    }
//	    mask.updatePixels();
//
//	    image(back.get(mouseX - RADIUS, mouseY - RADIUS, RADIUS * 2, RADIUS * 2), mouseX - RADIUS, mouseY - RADIUS);
//	    image(mask.get(mouseX - RADIUS, mouseY - RADIUS, RADIUS * 2, RADIUS * 2), mouseX - RADIUS, mouseY - RADIUS);
//	} else
//	    delay = 0;
//
//	textFont(font, 36);
//	// white float frameRate
//	fill(255);
//	println(frameRate);
//	// gray int frameRate display:
    
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
