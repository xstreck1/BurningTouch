package justaconcept.HiddenStory.main;

import processing.core.PImage;

public class GameState {
    static public int current_paper = 1; // Numerical id of the currently used paper 
    static public int latest_paper = 1; // Numerical id of the paper the player is currently solving
    static public boolean burned = false; // True after the player burns a paper
    
    static public PImage working_mask; // The text currently being elucidated 
    
    public enum Control {
	forward, backward, reset, none;
    }
    static public Control control = Control.none;
    static int mouse_x = -1;
    static int mouse_y = -1;
    static boolean mouse_pressed = false;
    
    public enum DrawWhat {
	all, scene, touch, nothing
    };
    public static DrawWhat draw_what = DrawWhat.all;
}
