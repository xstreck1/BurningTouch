package justaconcept.HiddenStory.main;

import processing.core.PApplet;
import processing.core.PImage;

public class StaticPaper extends BasicPaper {  
    StaticPaper(PApplet p_, int paper_no) {
	super(p_);
	this.clear_paper_ = p_.loadImage(Sources.CLEAR_PAPER);
	this.current_mask_ = p_.loadImage(Sources.getMaskName(paper_no, p_));
    }
}
