package event;

import java.util.EventObject;

public class GestureEvent extends EventObject {
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	/**
	 * Constructor
	 * @param source
	 */
	public GestureEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Return the name of the template
	 * @return String
	 */
	public String getTemplateName() {
		return name;
	}
	
	/**
	 * Return the score
	 * @return int
	 */
	public int getScore() {
		return score;
	}
}
