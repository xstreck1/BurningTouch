
public class hidden_story extends PApplet {
    PImage back;
    PImage front;
    PImage mask;
    PFont font;

    final int GAME_WIDTH = 480;
    final int GAME_HEIGHT = 320;

    public void setup() {
  size(GAME_WIDTH, GAME_HEIGHT);
  font = createFont("Arial", 48);

  orientation(LANDSCAPE);
  back = loadImage("papyrus.jpg");
  front = loadImage("papyrus_covered.jpg");
  mask = createImage(GAME_WIDTH, GAME_HEIGHT, ARGB);
  mask.loadPixels();
  front.loadPixels();
  for (int i = 0; i < front.pixels.length; i++) {
      int pix_color = front.pixels[i];
      int a = (pix_color >> 24) & 0xFF;
      int r = (pix_color >> 16) & 0xFF; // Faster way of getting red(argb)
      int g = (pix_color >> 8) & 0xFF; // Faster way of getting
               // green(argb)
      int b = pix_color & 0xFF;
      mask.pixels[i] = color(r, g, b, 2);
  }
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
      mask.pixels[position] = color(r, g, b, a + 1);
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
}

