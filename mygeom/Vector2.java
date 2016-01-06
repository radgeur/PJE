package mygeom;

public class Vector2 extends Tuple2 {
	
	public Vector2() {
		super();
	}
	
	public Vector2(Vector2 a) {
		super(a);
	}
	
	public Vector2(double x,double y) {
		super(x,y);
	}
	
	/**
	 * Calculate the dot
	 * @param other the vector to do the dot
	 * @return double the dot
	 */
	public double dot(Vector2 other) {
		return this.getX() * other.getX() + this.getY() * other.getY();
	}
	
	/**
	 * Calculate the determinant
	 * @param other the vector to do the determinant
	 * @return double the determinant
	 */
	public double determinant(Vector2 other) {	
		return this.getX() * other.getY() - this.getY() * other.getX();
	}
	
	/**
	 * Calculate the norm of a segment
	 * @param other
	 * @return
	 */
	public double norm() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}
	 
}