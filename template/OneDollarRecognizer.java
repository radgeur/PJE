package template;

import java.util.Vector;

import mygeom.Vector2;

public class OneDollarRecognizer {

	Vector<Vector2> points;
	
	/**
	 * Used to clear the point list
	 */
	public void clear() {
		if(points != null)
			points.clear();
	}
	
	/**
	 * Add the point to the list
	 * @param p Vector2
	 */
	public void add(Vector2 p) {
		if(points == null)
			points = new Vector<Vector2>();
		points.add(p);
	}
	
	/**
	 * return the equals template or null
	 * @return Template
	 */
	public Template getTemplate() {
		TemplateManager manager = new TemplateManager("data/gestures.xml");
		manager.getTemplates();
		
		return null;		
	}
}
