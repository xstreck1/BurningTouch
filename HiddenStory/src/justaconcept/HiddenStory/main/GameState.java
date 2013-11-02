package justaconcept.HiddenStory.main;

import processing.core.PImage;

public class GameState {
    static public PImage working_mask; // The text currently being elucidated 
    static public int current_paper = 1; // Numerical id of the currently used paper 
    static public int latest_paper = 1; // Numerical id of the paper the player is currently solving
    static public boolean burned = false; // True after the player burns a paper
}
