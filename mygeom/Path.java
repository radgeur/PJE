package mygeom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to define the Path of a Cursor
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

public class Path {
	
	//ATTRIBUTS
	List<Vector2> points;
	int id;
	
	//METHODS
	/**
	 * Constructor
	 * @param id of the Path
	 */
	public Path(int id) {
		points = new ArrayList<Vector2>();
		this.id = id;
	}
	
	/**
	 * Add a Point to the Path
	 * @param p
	 */
	public void add(Vector2 p) {
		points.add(p);
	}
	
	/**
	 * Clear a Path
	 */
	public void clear() {
		points.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g) {
		g.setPaint(Color.BLACK);
		GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
		if(points.size() > 0)
			polygon.moveTo(points.get(0).getX(), points.get(0).getY());

		for (int index = 1; index < points.size(); index++) {
		        polygon.lineTo(points.get(index).getX(), points.get(index).getY());
		}
		
		g.draw(polygon);
		if(points.size() > 0) {
			double xEllipse = points.get(points.size()-1).getX();
			double yEllipse = points.get(points.size()-1).getY();
			g.setPaint(Color.YELLOW);
			g.draw(new Ellipse2D.Double(xEllipse -10, yEllipse -10, 20, 20));
			g.drawString("" + id, (int) xEllipse , (int) yEllipse +5);
			
			Vector2 isoBary = this.getIsobarycentre();
			double xIso = isoBary.getX();
			double yIso = isoBary.getY();
			g.draw(new Ellipse2D.Double(xIso - 1, yIso - 1, 1, 1));
		} 
		
	}
	
	/**
	 * Return the position of the Path isobarycentre
	 * @return Vector2
	 */
	public Vector2 getIsobarycentre() {
		double xBary = 0;
		double yBary = 0;
		for (Vector2 point : this.points) {
			xBary +=  point.getX();
			yBary += point.getY();
		}
		xBary = xBary / this.points.size();
		yBary = yBary / this.points.size();
		return new Vector2(xBary, yBary);
	}

}
