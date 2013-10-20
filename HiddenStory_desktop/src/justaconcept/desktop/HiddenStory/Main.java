package justaconcept.desktop.HiddenStory;

import javax.swing.JFrame;

import main.justaconcept.HiddenStory.HiddenStory;

public class Main {
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable( false );
	HiddenStory hidden_story = new HiddenStory();
	frame.getContentPane().add(hidden_story);
	frame.setVisible(true);
	hidden_story.init();
	int width = hidden_story.sketchWidth();
	int height = hidden_story.sketchHeight();
	frame.setSize(width, height);
    }
}
