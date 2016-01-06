package widget;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import mygeom.Vector2;
/**
 * Class to define a component who is a picture
 * @author LEPRETRE Rémy and STAMPER Corentin
 *
 */

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
		super();
		img = null;
		try {
			img = ImageIO.read(new File(imageName));
		} catch(Exception e) {
			System.out.println(imageName);
			System.out.println("Erreur : " + e.getMessage());
			return;
		}
		/*obb.setPosition(0, img.getWidth(null), img.getHeight(null), new Vector2(0.0, 0.0));
		obb.setOrigine(new Vector2(0, 0));
		obb.setWidth(img.getWidth(null));
		obb.setHeight(img.getHeight(null));*/
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(Graphics2D g) {
		System.out.println(obb.getOrigine().getX());
		g.drawImage(img, (int) obb.getOrigine().getX(), (int) obb.getOrigine().getY(), null);
	}
}
