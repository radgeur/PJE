package event;

import java.awt.AWTEvent;

public abstract class ChangedSideEvent extends AWTEvent{

	private static final long serialVersionUID = 4887871342211853783L;
	private int id;
	
	public ChangedSideEvent(Object o, int id) {
		super(o, id);
		this.id = id;
	}
	
	public int getCursorId() {
		return id;
	}
	
}
