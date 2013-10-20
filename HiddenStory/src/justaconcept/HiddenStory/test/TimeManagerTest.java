package justaconcept.HiddenStory.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import justaconcept.HiddenStory.main.TimeManager;

import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;

public class TimeManagerTest {
    TimeManager time_manager;
    
    @Before
    public void setUp() throws Exception {
	PApplet applet = new PApplet();
	time_manager = new TimeManager(applet, 30);
    }

    @Test
    public void testGetDelta_() throws InterruptedException {
	Thread.sleep(1);
	time_manager.update();
	assertTrue(time_manager.getDelta_() > 0);
    }

    @Test
    public void testGetFramerate_() {
	assertEquals(30, time_manager.getFramerate_());
	assertEquals(30, time_manager.toFrames(1000));
	assertEquals(1000, time_manager.toTime(30));
    }

}
