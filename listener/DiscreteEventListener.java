package listener;

import java.util.EventListener;

import event.DiscreteEvent;

/**
 * Implement a listener to know when a cursor pop
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public interface DiscreteEventListener extends EventListener{
	
	public void gesturePerformed(DiscreteEvent e);
	
}
