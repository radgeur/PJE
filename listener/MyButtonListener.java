package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import widget.MTSurface;

/**
 * Implement a listener to know when someone click on a button
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

public class MyButtonListener implements ActionListener {

	MTSurface surf;
	
	public MyButtonListener(MTSurface surf) {
		super();
		this.surf = surf;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		boolean visible = surf.invCursorVisibility();
		if(visible)
			b.setText("desactiver");
		else
			b.setText("activer");
	}

}
