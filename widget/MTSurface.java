package widget;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import listener.ChangedSideListener;
import mygeom.BlobQueue;
import mygeom.ComponentMap;
import mygeom.Vector2;
import tuio.MTedt;

/**
 * Class who extend a JPanel
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public class MTSurface extends JPanel {
	
	//ATTRIBUTS
	private static final long serialVersionUID = 1L;
	private MTedt edt;
	private boolean cursorVisible;
	private ChangedSideListener listener;
	private MTContainer container;
	private ComponentMap cMap;
	
	
	//METHODS
	/**
	 * Constructor
	 */
	public MTSurface() {
		super();
		edt = new MTedt(this); 
		cMap = new ComponentMap();
		cursorVisible = true;
		container = new MTContainer();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		container.draw(g2);
		if(cursorVisible) {
			for (BlobQueue bQ: cMap.getcMap().values()) {
				bQ.draw(g2);
			}
		}
	}
	
	/**
	 * Add a cursor on the surface
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public synchronized void addCursor(int id, Vector2 p) {
		MTComponent comp = container.whichIs(p);
		if(comp != null) {
			comp.click();
			cMap.addBlob(comp, id, p);
			repaint();		
			System.out.println("add id=" + id + "; Point=(" + p.getX() + ", " + p.getY() + ")");
		}
	}

	/**
	 * Method to update a cursor
	 * @param id of the cursor
	 * @param p new position of the cursor
	 */
	public synchronized void updateCursor(int id, Vector2 p) {
		cMap.updateBlob(id, p);
		repaint();		
		System.out.println("update id=" + id + "; Point=(" + p.getX() + ", " + p.getY() + ")");
	}
	
	/**
	 * Method to remove a cursor
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public synchronized void removeCursor(int id, Vector2 p) {
		cMap.removeBlob(id, p);
		repaint();
		System.out.println("remove id=" + id + "; Point=(" + p.getX() + ", " + p.getY() + ")");
	}
	
	/**
	 * Method to change the visibility of all the cursor. TRUE the cursor are visible.
	 * @return boolean
	 */
	public boolean invCursorVisibility() {
		cursorVisible = !cursorVisible;
		repaint();
		return cursorVisible;
	}
	
	
	public void addChangedSideListener(ChangedSideListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Add a component to the container
	 * @param comp the component to add
	 */
	public void add(MTComponent comp) {
		container.add(comp);
	}
	
	/**
	 * return the container of the surface
	 * @return MTComponent
	 */
	public MTComponent getContainer() {
		return this.container;
	}
}
