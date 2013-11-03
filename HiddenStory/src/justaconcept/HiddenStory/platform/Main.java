package justaconcept.HiddenStory.platform;

import javax.swing.JFrame;

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
	frame.setSize(Platform.getScreenWidth() + 20, Platform.getScreenHeight() + 60);
    }
}
