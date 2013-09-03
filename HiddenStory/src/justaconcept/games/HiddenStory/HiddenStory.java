package justaconcept.games.HiddenStory;
import processing.core.*;

public class HiddenStory extends PApplet {
    private PImage back;
    private PImage front;
    private PImage mask;
    private PFont font;

    private final int GAME_WIDTH = 480;
    private final int GAME_HEIGHT = 320;

    public void setup() {

	frameRate(30);
	orientation(LANDSCAPE);
	size(GAME_WIDTH, GAME_HEIGHT);
	
	font = createFont("Arial", 48);
	back = loadImage("papyrus.png");
	front = loadImage("papyrus_covered_base.png");
	mask = createImage(GAME_WIDTH, GAME_HEIGHT, ARGB);
	mask.loadPixels();
	front.loadPixels();
	for (int i = 0; i < front.pixels.length; i++) {
	    int pix_color = front.pixels[i];
	    int a = 0; // (pix_color >> 24) & 0xFF;
	    int r = (pix_color >> 16) & 0xFF; // Faster way of getting red(argb)
	    int g = (pix_color >> 8) & 0xFF; // Faster way of getting
					     // green(argb)
	    int b = pix_color & 0xFF;
	    mask.pixels[i] = color(r, g, b, a);
	}
	front.loadPixels();
	mask.updatePixels();
	background(back);
	loop();
    }

    int delay = 0;

    final int DIAMETER = 60;
    final int RADIUS = DIAMETER / 2;
    final int SQR_RAD = (RADIUS) * (RADIUS);

    public void draw() {
	if (mousePressed) {
	    mask.loadPixels();
	    delay++;
	    for (int x = max(0, mouseX - DIAMETER / 2); x < min(GAME_WIDTH, mouseX + DIAMETER / 2); x++) {
		int span = (int) Math.round(Math.sqrt(SQR_RAD - ((x - mouseX) * (x - mouseX))));
		for (int y = max(0, mouseY - span); y < min(GAME_HEIGHT, mouseY + span); y++) {
		    int position = y * 480 + x;
		    int pix_color = mask.pixels[position];
		    int a = (pix_color >> 24) & 0xFF;
		    int r = (pix_color >> 16) & 0xFF; // Faster way of getting
						      // red(argb)
		    int g = (pix_color >> 8) & 0xFF; // Faster way of getting
						     // green(argb)
		    int b = pix_color & 0xFF;
		    if (a < 0xFF)
			mask.pixels[position] = color(r, g, b, a + 5);
		    if (delay > 10)
			mask.pixels[position] = color(r - 2, g - 2, b - 5, a + 1);
		}
	    }
	    mask.updatePixels();

	    image(back.get(mouseX - RADIUS, mouseY - RADIUS, RADIUS * 2, RADIUS * 2), mouseX - RADIUS, mouseY - RADIUS);
	    image(mask.get(mouseX - RADIUS, mouseY - RADIUS, RADIUS * 2, RADIUS * 2), mouseX - RADIUS, mouseY - RADIUS);
	} else
	    delay = 0;

	textFont(font, 36);
	// white float frameRate
	fill(255);
	println(frameRate);
	// gray int frameRate display:
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
        return GAME_WIDTH;
    }

    public int sketchHeight() {
        return GAME_HEIGHT;
    }
}
