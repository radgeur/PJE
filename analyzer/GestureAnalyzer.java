package analyzer;

import java.util.Iterator;
import java.util.Map.Entry;

import mygeom.BlobQueue;
import mygeom.Path;
import mygeom.Vector2;
import template.TemplateManager;
import widget.InternalGestureState;
import widget.MTComponent;
import event.DiscreteEvent;
import event.SRTEvent;
//import template.TemplateManager;

/**
 * Class to analyze the different moves
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public class GestureAnalyzer {
	
	//ATTRIBUTS
	public final static String STATE_ADD = "add";
	public final static String STATE_UPDATE = "update";
	public final static String STATE_REMOVE = "remove";

	private InternalGestureState iGS;
	
	//METHODS
	/**
	 * Analyze the situation to do the good action
	 * @param comp the component
	 * @param bQ the component BlobQueue
	 * @param state
	 * @param id of the cursor
	 * @param p position of the cursor
	 */
	public void analyze(MTComponent comp, BlobQueue bQ, String state, int id, Vector2 p) {
		if (STATE_ADD.equals(state)) {
			if(bQ.nbCursors() == 1) {
				add(comp, p, null);
				iGS.recognizer.clear();
			}
			if(bQ.nbCursors() == 2)
				add(comp, null, p);
			comp.fireDiscretePerformed(new DiscreteEvent(comp));
		} else if(STATE_UPDATE.equals(state)) {
			if(bQ.nbCursors() == 1) {
				update(comp, p, null);
				comp.fireSRTPerformed(new SRTEvent(comp, iGS.computeTranslation(), 0, 1));
				iGS.recognizer.add(p);
			} else if(bQ.nbCursors() == 2) {
				Iterator<Entry<Integer, Path>> entry = bQ.getCursors().entrySet().iterator();
				if(entry.next().getKey() == id)
					update(comp, p, null);
				if(entry.next().getKey() == id)
					update(comp, null, p);
				System.out.println("angle " + iGS.computeTRSRotation());
				double angle = iGS.computeTRSRotation();
				if (angle != 0)
					comp.fireSRTPerformed(new SRTEvent(comp, new Vector2(0,0), iGS.computeTRSRotation(), iGS.computeTRSScale()));
				else
					comp.fireSRTPerformed(new SRTEvent(comp, iGS.computeTRSTranslate(), 0.0, iGS.computeTRSScale()));
			}
		} else if(STATE_REMOVE.equals(state)) {
			if(bQ.nbCursors() == 0) {
				remove();
				System.out.println(new TemplateManager("data/gestures.xml").getTemplates());
//				comp.fireGesturePerformed(new GestureEvent());
			} else 
				add(comp, p, null);
		}
	}
	
	/**
	 * In terms of who vector is null, add a cursor to the internalGestureState
	 * @param comp component on who do the motion
	 * @param pA the vectorA
	 * @param pB the VectorB
	 */
	private void add(MTComponent comp, Vector2 pA, Vector2 pB) {
		iGS = new InternalGestureState(comp);
		if(pA != null)
			iGS.motionTranslateBegin(pA);
		else
			iGS.motionTRSBegin(pA, pB);
	}

	/**
	 * In terms of who vector is null, update cursor(s) on the internalGestureState
	 * @param comp component on who do the motion
	 * @param pA the vectorA
	 * @param pB the VectorB
	 */
	private void update(MTComponent comp, Vector2 pA, Vector2 pB) {
		if(pA != null)
			iGS.motionTranslateUpdate(pA);
		else
			iGS.motionTRSUpdate(pA, pB);
	}
	
	/**
	 * clear the internalGestureState
	 */
	private void remove() {
		iGS = null;
	}
}
