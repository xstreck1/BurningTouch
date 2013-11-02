package justaconcept.HiddenStory.test;

import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import justaconcept.HiddenStory.main.Constants;
import justaconcept.HiddenStory.main.HiddenStory;

public class Main {
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable( false );
	HiddenStory applet = new HiddenStory();
	frame.getContentPane().add(applet);
	frame.setVisible(true);
	applet.init();
	frame.setSize(Platform.SCREEN_WIDTH, Platform.SCREEN_HEIGHT);
    }
}
