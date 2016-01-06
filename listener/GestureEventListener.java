package listener;

import java.util.EventListener;

import event.GestureEvent;

public interface GestureEventListener extends EventListener {
	
	public void gesturePerformed(GestureEvent e);

}
