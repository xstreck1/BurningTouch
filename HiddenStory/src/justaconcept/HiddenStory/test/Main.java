package justaconcept.HiddenStory.test;

import javax.swing.JFrame;

import justaconcept.HiddenStory.main.HiddenStory;
import justaconcept.HiddenStory.platform.Platform;

public class Main {
    public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable( false );
	HiddenStory applet = new HiddenStory();
	frame.getContentPane().add(applet);
	frame.setVisible(true);
	applet.init();
	frame.setSize(Platform.getScreenWidth(), Platform.getScreenHeight());
    }
}
