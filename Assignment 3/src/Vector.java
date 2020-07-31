//package cloudscapes;
/**
 * The Vector class stores Vector objects which contain an x 
 * component and a y component.
 * @author Leanne January
 */
public class Vector {
	public float x, y;

	/**
	 * A default constructor method
	 */
	Vector () {}

	/** 
	 * A constructor method for two floats.
	 * @param x the x-component
	 * @param y the y-component
	 */
	Vector (float x, float y) {
		this.x = x;
		this.y = y;
	}

	/** 
	 * A method for adding another vector to this one
	 * @param v the vector to be added
	 */
	Vector add(Vector v){
		return new Vector(this.x+v.x, this.y+v.y);
	}
}
