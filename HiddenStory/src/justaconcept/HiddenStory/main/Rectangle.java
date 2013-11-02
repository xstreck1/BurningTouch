package justaconcept.HiddenStory.main;

public class Rectangle {
    public int x_;
    public int y_;
    public int width_;
    public int height_;
    
    public Rectangle() {
	x_ = y_ = width_ = height_ = 0;
    }
    
    public Rectangle(int x_, int y_, int width_, int height_) {
	super();
	this.x_ = x_;
	this.y_ = y_;
	this.width_ = width_;
	this.height_ = height_;
    }
    
    public boolean holdsPoint(int x, int y) {
	return (x >= x_ && y >= y_ && x <= (x_ - width_) && y <- (y_ - height_));
    }
}
