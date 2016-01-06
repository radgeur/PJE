package widget;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import mygeom.Vector2;

/**
 * Class to define a Component who is a container of components
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

public class MTContainer extends MTComponent{

	//ATTRIBUTS
	private static final long serialVersionUID = 1L;
	private List<MTComponent> components;
	
	//METHODS
	/**
	 * Constructor
	 */
	public MTContainer () {
		components = new ArrayList<MTComponent>();
	}
	
	/**
	 * Add a component to the list of components
	 * @param comp
	 */
	public void add(MTComponent comp) {
		components.add(comp);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g) {
		AffineTransform save = g.getTransform();
		
		for(MTComponent component : components) {
			g.translate(component.getObb().getOrigine().getX(), component.getObb().getOrigine().getY());
			g.rotate(component.getObb().getAngle());
			g.scale(component.getObb().getWidth()/component.getWidth(), component.getObb().getHeight()/component.getHeight());
			component.draw(g);
			g.setTransform(save);
		}			
	}
	
	/**
	 * method to determinate of which component the cursor is
	 * @param p the position of the cursor
	 * @return
	 */
	public MTComponent whichIs (Vector2 p) {
		for(int i=components.size()-1; i>=0; i--) {
			MTComponent component = components.get(i);
			component.registerContainer(this);
			if (component.isInside(p))
				return component;
		}
		return null;
	}
	
	/**
	 * Select the active component to put it on the foreground
	 * @param comp the selected component
	 */
	public void select(MTComponent comp) {
		components.remove(comp);
		components.add(comp);
	}
}
