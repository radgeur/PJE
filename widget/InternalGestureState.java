package widget;

import mygeom.Vector2;
import template.OneDollarRecognizer;

public class InternalGestureState {
	// position d'une OBB 
	OBB oldOBB,currentOBB; 
	// Etats du curseur pour la translation simple
	Vector2 oldPosA, currentPosA;
	Vector2 oldPosB, currentPosB;
	MTComponent c;
	public OneDollarRecognizer recognizer;
	
	public InternalGestureState(MTComponent c) { 
		// le currentOBB sera une référence sur l'OBB de c 
		// donc : lorsque currentOBB est modifié, cela modifie l'OBB de c (pas de new pour currentOBB !!!!!) 
		oldPosA=new Vector2(); // pour mémoriser la position précédente du curseur 
		currentPosA=new Vector2(); 
		oldOBB = new OBB(); // pour mémoriser la position précédente, si besoin 
		currentOBB = c.getObb(); // référence sur l'obb du composant ... }
		this.c = c;
		recognizer = new OneDollarRecognizer(); 
	}
	
	/**
	 * The cursor became the currentPos
	 * @param cursor
	 */
	public void motionTranslateBegin(Vector2 cursor) {
		this.currentPosA = cursor;
	}
	
	/**
	 * The cursor has moved so the position is update
	 * @param cursor
	 */
	public void motionTranslateUpdate(Vector2 cursor) {
		this.oldPosA = this.currentPosA;
		this.currentPosA = cursor;
		this.oldOBB = currentOBB;
		this.currentOBB = c.getObb();
	}
	
	/**
	 * Calculate the translation vector
	 * @return Vector2 Translation vector
	 */
	public Vector2 computeTranslation() {
		double x = 0.0, y = 0.0;
		x = currentPosA.getX() - oldPosA.getX();
		y = currentPosA.getY() - oldPosA.getY();
		return new Vector2(x, y);
	}
	
	/**
	 * Add a second cursor
	 * @param cursorA the first cursor
	 * @param cursorB the second cursor
	 */
	public void motionTRSBegin(Vector2 cursorA, Vector2 cursorB){
		this.currentPosB = cursorB;
	}
	
	/**
	 * Update the cursor if they aren't null
	 * @param cursorA the first cursor
	 * @param cursorB the second cursor
	 */
	public void motionTRSUpdate(Vector2 cursorA, Vector2 cursorB){
		if(cursorA != null) { // cursorA updated
			this.oldPosA = this.currentPosA;
			this.currentPosA = cursorA;
		}
		if(cursorB != null) {  // cursorB updated
			this.oldPosB = this.currentPosB;
			this.currentPosB = cursorB;
		}
		this.oldOBB = currentOBB;
		this.currentOBB = c.getObb();
	}
	
	/**
	 * Calculate the Scale
	 * @return double the scale
	 */
	public double computeTRSScale() {
		try {
			double oldDist = Math.sqrt(Math.pow((oldPosB.getX()-oldPosA.getX()), 2) + Math.pow((oldPosB.getY()-oldPosA.getY()), 2));
			double curDist = Math.sqrt(Math.pow((currentPosB.getX()-currentPosA.getX()), 2) + Math.pow((currentPosB.getY()-currentPosA.getY()), 2));
			return curDist / oldDist;
		} catch(NullPointerException e) {
			System.out.println("NullPointer computeTRSScale : " + e.getMessage());
			return 1.0;
		}
	}
	
	/**
	 * Calculate the rotation
	 * @return double the angle
	 */
	public double computeTRSRotation() {
		Vector2 old = new Vector2(oldPosB.getX() - oldPosA.getX(), oldPosB.getY() - oldPosA.getY());
		Vector2 current = new Vector2(currentPosB.getX() - currentPosA.getX(), currentPosB.getY() - currentPosA.getY());
		double angle = Math.acos((old.dot(current)) / (old.norm() * current.norm()));
		double det = old.determinant(current);
		if(det == 0)
			return 0;
		return det>=0 ? angle: -angle;
	}
	
	
	public Vector2 computeTRSTranslate() {
		Vector2 posA = computeTranslation();
		double x = 0.0, y = 0.0;
		x = currentPosB.getX() - oldPosB.getX();
		y = currentPosB.getY() - oldPosB.getY();
		Vector2 posB = new Vector2(x, y);
		return new Vector2((posA.getX() + posB.getX())/2.0,(posA.getY() + posB.getY())/2.0);
	}
}
