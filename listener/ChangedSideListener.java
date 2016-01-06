package listener;

import java.util.EventListener;

import event.ChangedSideEvent;

/**
 * Implement a Listener to know when the cursor change of side
 * @author LEPRETRE R�my and STAMPER Corentin
 *
 */

public interface ChangedSideListener extends EventListener {
	
	public void changedSidePerformed(ChangedSideEvent evt) ;
}
