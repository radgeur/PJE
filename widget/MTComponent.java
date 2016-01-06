package widget;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

import listener.DiscreteEventListener;
import listener.GestureEventListener;
import listener.SRTEventListener;
import mygeom.Vector2;
import event.DiscreteEvent;
import event.GestureEvent;
import event.SRTEvent;
/**
 * Class to define a component
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public class MTComponent extends JComponent{
	//ATTRIBUTS
	private static final long serialVersionUID = 1L;
	/**
	 * List of the EvenLister of the Component
	 */
	private EventListenerList eventList;
	/**
	 * the container of the Component
	 */
	private MTContainer container;
	private OBB obb;
	
	
	//METHODS
	public MTComponent() {
		super();
		eventList = new EventListenerList();
		obb = new OBB(0, 0, 0, new Vector2(0.0, 0.0));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g){}
	
	/**
	 * Return the position of the component
	 * @return Vector2
	 */
	public Vector2 getPosition() {return position;}

	/**
	 * Set the position of the component
	 * @param position the new position of the component
	 */
	public void setPosition(Vector2 position) {this.position = position;}

	/**
	 * Return the width of the component
	 * @return int
	 */
	public int getWidth() {return width;}

	/**
	 * Set the width of the component
	 * @param width the new width of the component
	 */
	public void setWidth(int width) {this.width = width;}

	/**
	 * Return the height of the component
	 * @return int
	 */
	public int getHeight() {return height;}

	/**
	 * Set the height of the Component
	 * @param height the new height of the component
	 */
	public void setHeight(int height) {this.height = height;}
	
	/**
	 * Set the position of the Component and his OBB
	 * @param angle
	 * @param width
	 * @param height
	 * @param p
	 */
	public void setPosition (double angle, double width, double height, Vector2 p) {
		this.obb.setPosition(angle, width, height, p);
	}
	
	/**
	 * Return the OBB of the component
	 * @return OBB
	 */
	public OBB getObb() {return this.obb;}
	
	/**
	 * Check if the cursor is inside the Component
	 * @param p position of the cursor
	 * @return boolean
	 */
	public boolean isInside(Vector2 p) {
		AffineTransform transform = new AffineTransform();
		transform.setToIdentity();
		transform.scale(1/obb.getWidth(), 1/obb.getHeight());
		transform.rotate(-obb.getAngle());
		transform.translate(-obb.getOrigine().getX(), -obb.getOrigine().getY());
		Point2D oldObb = new Point2D.Double(p.getX(), p.getY());
		Point2D newObb = new Point2D.Double();
		transform.transform(oldObb, newObb);
		boolean checkX = newObb.getX() >= 0 && newObb.getX() <= 1;
		boolean checkY = newObb.getY() >= 0 && newObb.getY() <= 1;
		return checkX && checkY;
	}
	
	public void addDiscreteEventListener(DiscreteEventListener e) {
		eventList.add(DiscreteEventListener.class, e);
	}
	
	public void addSRTEventListener(SRTEventListener e) {
		eventList.add(SRTEventListener.class, e);
	}
	
	public void addGestureEventListener(GestureEventListener e) {
		eventList.add(GestureEventListener.class, e);
	}
	
	public void fireDiscretePerformed(DiscreteEvent e) {
	     Object[] listeners = eventList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i] == DiscreteEventListener.class) {
	             ((DiscreteEventListener)listeners[i+1]).gesturePerformed(e);
	         }
	     }
	}
	
	public void fireSRTPerformed(SRTEvent e) {
		 Object[] listeners = eventList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i] == SRTEventListener.class) {
	             ((SRTEventListener)listeners[i+1]).gesturePerformed(e);
	         }
	     }
	}
	
	public void fireSRTPerformed(GestureEvent e) {
		Object[] listeners = eventList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i] == GestureEventListener.class) {
	             ((GestureEventListener)listeners[i+1]).gesturePerformed(e);
	         }
	     }
	}
	
	/**
	 * Add the component to a container
	 * @param container
	 */
	public void registerContainer(MTContainer container) {
		if(this.container == null)
			this.container = container;
	}
	
	public void click() {
		if(container == null)
			System.out.println("Container was not initialized");
		else
			container.select(this);
	}
}
