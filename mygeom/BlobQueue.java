package mygeom;

import java.awt.Graphics2D;

/**
 * Class to define a BlobQueue who contain different Path
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class BlobQueue {
	//ATTRIBUTS
	/**
	 * HasMap with one Integer for one Path of a cursor
	 */
	HashMap<Integer,Path> cursors;

	//METHODS
	/**
	 * Constructor
	 */
	public BlobQueue() {
		cursors = new HashMap<Integer,Path>();
	}
	
	/**
	 * Add a cursor with it integer to the hashMap
	 * @param id of the cursor
	 * @param start the start point of the Path
	 */
	public void addCursor(Integer id, Vector2 start) {
		Path p = new Path(id);
		p.add(start);
		cursors.put(id, p);
	}
	
	/**
	 * Remove a cursor to the HashMap
	 * @param id of the cursor to remove
	 * @param p
	 */
	public void removeCursor(Integer id, Vector2 p) {
		cursors.remove(id);
	}
	
	/**
	 * Add a position of the cursor to it Path in the HasMap
	 * @param id of the cursor
	 * @param p new Point of the Path
	 */
	public void updateCursor(Integer id, Vector2 p) {
		cursors.get(id).add(p);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g) {
		
		Set<Integer> ids = cursors.keySet();
		Iterator<Integer> it = ids.iterator();
		while(it.hasNext())
			cursors.get(it.next()).draw(g);
	}
	
	/**
	 * Check if the cursor id is in the HashMap
	 * @param id of the cursor
	 * @return boolean
	 */
	public boolean checkId(int id) {
		return cursors.containsKey(id);
	}
	
	/**
	 * Return the number of cursor in the HashMap
	 * @return int
	 */
	public int nbCursors() {
		return cursors.size();
	}
	
	/**
	 * Return the HashMap of cursors for the BlobQueue
	 * @Return Map
	 */
	public HashMap<Integer,Path> getCursors(){
		return cursors;
	}
}
