package widget;

import java.awt.Graphics2D;

/**
 * Class to define a component who is a picture
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import mygeom.Vector2;

public class MTPicture extends MTComponent {

	//ATTRIBUTS
	private static final long serialVersionUID = 1L;

	private Image img;
	
	//METHODS
	/**
	 * Constructor
	 * @param imageName the name of the picture
	 */
	public MTPicture (String imageName) {
		img = null;
		try {
			img = ImageIO.read(new File(imageName));
		} catch(Exception e) {
			System.out.println(imageName);
			System.out.println("Erreur : " + e.getMessage());
			return;
		}
		setPosition(new Vector2(0, 0));
		setWidth(img.getWidth(null));
		setHeight(img.getHeight(null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g) {
		g.drawImage(img, (int) getPosition().getX(), (int) getPosition().getY(), null);
	}
}
