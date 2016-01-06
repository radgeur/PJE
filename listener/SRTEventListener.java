package listener;

import java.util.EventListener;

import event.SRTEvent;

/**
 * Implement a listener to know when a SRT is do
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

public interface SRTEventListener extends EventListener{
	
	public void gesturePerformed(SRTEvent e);

}
