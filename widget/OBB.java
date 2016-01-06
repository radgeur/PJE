package widget;

import mygeom.Vector2;

/**
 * Class to define the OBB of a component
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public class OBB {
	
	//ATTRIBUTS
	private double angle, width, height;
	private Vector2 origine;
	
	
	//METHODS
	public OBB() {}
	
	/**
	 * Constructor
	 * @param angle
	 * @param width
	 * @param height
	 * @param p
	 */
	public OBB(double angle, double width, double height, Vector2 p){
		this.angle = angle;
		this.width = width;
		this.height = height;
		this.origine = p;
	}
	
	/**
	 * Return the angle of the OBB
	 * @return double
	 */
	public double getAngle() {return angle;}
	
	/**
	 * Return the width of the OBB
	 * @return int
	 */
	public double getWidth() {return width;}
	
	/**
	 * Return the height of the OBB
	 * @return int
	 */
	public double getHeight() {return height;}
	
	/**
	 * Return the position of the OBB
	 * @return Vector2
	 */
	public Vector2 getOrigine() {return origine;}
	

	/**
	 * Set all attributs of the OBB
	 * @param angle
	 * @param width
	 * @param height
	 * @param p
	 */
	public void setPosition(double angle, double width, double height, Vector2 p){
		this.angle = angle;
		this.width = width;
		this.height = height;
		this.origine = p;
	}
	
	/**
	 * update the position of the OBB's component
	 * @param t
	 * @param angle
	 * @param k
	 */
	public void updatePosition(Vector2 t, double angle, double k) {
		Vector2 tmp = new Vector2(t.getX() + origine.getX(), t.getY() + origine.getY());
		this.setPosition(this.angle + angle, width * k, height * k, tmp);
	}
	
}
