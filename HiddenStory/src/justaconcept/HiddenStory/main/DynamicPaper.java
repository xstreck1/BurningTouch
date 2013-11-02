package justaconcept.HiddenStory.main;

import processing.core.PApplet;
import processing.core.PImage;

public class DynamicPaper extends BasicPaper {
    DynamicPaper(PApplet p_, PImage current_mask_) {
	super(p_);
	this.clear_paper_ = p_.loadImage(Sources.CLEAR_PAPER);
	this.current_mask_ = current_mask_;
    }
}
