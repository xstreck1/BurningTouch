package justaconcept.HiddenStory.main;

import processing.core.PApplet;

public class PerPixelFunc {
    static int makeInvis(final PApplet p_, final int pix_color) {
	int a = 0; // (pix_color >> 24) & 0xFF;
	int r = (pix_color >> 16) & 0xFF; // Faster way of getting red(argb)
	int g = (pix_color >> 8) & 0xFF; // Faster way of getting// green(argb)
	int b = pix_color & 0xFF;
	return p_.color(r, g, b, 1); //< Alpha is set to 1 because of some Android-related troubles.
    }
    
    static int incAlpha(final PApplet p_, final int pix_color, final int step) {
	int a = (pix_color >> 24) & 0xFF;
	int r = (pix_color >> 16) & 0xFF; // Faster way of getting red(argb)
	int g = (pix_color >> 8) & 0xFF; // Faster way of getting// green(argb)
	int b = pix_color & 0xFF;
	// Increase alpha up to 255 for a non-white pixel
	if (r != 255 && g != 255 && b != 255 && a != 0)
	    a = p_.min(255, a + step);
	return p_.color(r, g, b, a);
    }
}
