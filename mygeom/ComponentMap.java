package mygeom;

import java.util.HashMap;
import java.util.Map;

import analyzer.GestureAnalyzer;
import widget.MTComponent;

/**
 * Class to define a ComponentMap who contain a Map with Component and our BlobQueue
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

public class ComponentMap {

	// ATTRIBUTS
	Map<MTComponent, BlobQueue> cMap;
	GestureAnalyzer analyzer;
	
	
	//METHODS
	/**
	 * Constructor
	 */
	public ComponentMap() {
		cMap = new HashMap<MTComponent, BlobQueue>();
		analyzer = new GestureAnalyzer();
	}
	
	/**
	 * Add a BlobQueue to a component
	 * @param comp 
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public void addBlob(MTComponent comp, int id, Vector2 p) {
		BlobQueue blob = cMap.get(comp);
		if(blob == null)
			blob = new BlobQueue();
		blob.addCursor(id,p);
		cMap.put(comp, blob);
		analyzer.analyze(comp, blob, GestureAnalyzer.STATE_ADD, id, p);
	}
	
	/**
	 * Update the BlobQueue of a component
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public void updateBlob(int id, Vector2 p){
		for(MTComponent comp : cMap.keySet()) {
			if(cMap.get(comp).checkId(id)) {
				cMap.get(comp).updateCursor(id, p);
				analyzer.analyze(comp, cMap.get(comp), GestureAnalyzer.STATE_UPDATE, id, p);
			}
		}
	}
	
	/**
	 * Remove a BlobQueue of a component
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public void removeBlob(int id, Vector2 p) {
		for(MTComponent comp : cMap.keySet()) {		
			if(cMap.get(comp).checkId(id)) {
				cMap.get(comp).removeCursor(id, p);
				analyzer.analyze(comp, cMap.get(comp), GestureAnalyzer.STATE_REMOVE, id, p);
			}
		}
	}

	/**
	 * Return the HashMap
	 * @return Map
	 */
	public Map<MTComponent, BlobQueue> getcMap() {
		return cMap;
	}
}