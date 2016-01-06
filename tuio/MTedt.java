package tuio;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import main.Main;
import mygeom.Vector2;
import widget.MTSurface;

public class MTedt implements TuioListener {

	TuioClient client=null;
	MTSurface surface; // la surface qui recevra les messages des curseurs.
	
	private void initConnexion() {
		int port=3333;
		client=new TuioClient(port);
		client.connect();
		if (!client.isConnected()) {
			System.exit(1);
		}
		System.out.println("connexion");		
	}
	
	public MTedt() {
		initConnexion();
		client.addTuioListener(this);
	}
	
	public MTedt(MTSurface s) {
		initConnexion();
		surface=s;
		client.addTuioListener(this);
	}
	
	public void stop() {
		client.disconnect();
		System.out.println("deconnexion");
	}
	
		
	/** Listeners **/
	

	public void addTuioCursor(TuioCursor arg0) {
		surface.addCursor(arg0.getCursorID(), new Vector2(arg0.getX()*Main.WINDOW_Width, arg0.getY()*Main.WINDOW_Height));
		
	}

	public void addTuioObject(TuioObject arg0) {
		
		
	}

	public void refresh(TuioTime arg0) {
		// TODO Auto-generated method stub
		
	}

	public void removeTuioCursor(TuioCursor arg0) {
		surface.removeCursor(arg0.getCursorID(), new Vector2(arg0.getX()*Main.WINDOW_Width, arg0.getY()*Main.WINDOW_Height));
		
	}

	public void removeTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

	public void updateTuioCursor(TuioCursor arg0) {
		surface.updateCursor(arg0.getCursorID(), new Vector2(arg0.getX()*Main.WINDOW_Width, arg0.getY()*Main.WINDOW_Height));
		
	}

	public void updateTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

}