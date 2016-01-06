package event;

import java.util.EventObject;

import mygeom.Vector2;

public class SRTEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private Vector2 translation;
	private double angle;
	private double scale;
	
	public SRTEvent(Object source, Vector2 translation, double angle, double scale) {
		super(source);
		this.translation = translation;
		this.angle = angle;
		this.scale = scale;
	}

	public Vector2 getTranslation() {
		return translation;
	}

	public void setTranslation(Vector2 translation) {
		this.translation = translation;
	}
	
	public double getAngle() {
		return angle;
	}

	public double getScale() {
		return scale;
	}
}
