package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import listener.DiscreteEventListener;
import listener.GestureEventListener;
import listener.MyButtonListener;
import listener.SRTEventListener;
import mygeom.Vector2;
import widget.MTComponent;
import widget.MTPicture;
import widget.MTSurface;
import event.DiscreteEvent;
import event.GestureEvent;
import event.SRTEvent;

public class Main {
	
	/**
	 * Width and Height of the Window
	 */
	public static final int WINDOW_Width=500;
	public static final int WINDOW_Height=500;
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        createGui();
		    }
		});
	}	
	
	public static void createGui(){
		//initialize the JFrame
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.setMinimumSize(new Dimension(550,550));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//Add a surface on the JFrame
		MTSurface surf = new MTSurface();
		surf.setBackground(new Color(80, 80, 255));
		surf.setPreferredSize(new Dimension(WINDOW_Width,WINDOW_Height));
		surf.setBorder(BorderFactory.createLineBorder(Color.RED));
		JButton cursorVisible = new JButton("desactiver");
		cursorVisible.addActionListener(new MyButtonListener(surf));
		surf.add(cursorVisible);
		f.getContentPane().add(surf);
		
		//A listener to know when a a cursor pop
		DiscreteEventListener dListener = new DiscreteEventListener() {
			
			public void gesturePerformed(DiscreteEvent e) {
				System.out.println("discreteEvent");
			}
		};
		
		//A listener to do the SRT moves
		SRTEventListener srtListener = new SRTEventListener() {

			public void gesturePerformed(SRTEvent e) {
				MTComponent comp = (MTComponent) e.getSource();
				Vector2 translation = e.getTranslation();
				double k = e.getScale();
				comp.getObb().updatePosition(translation,e.getAngle(), k);
				comp.repaint();
			}
			
		};
		
		//A listener to analyse the gesture
		GestureEventListener gestureListener = new GestureEventListener() {
			public void gesturePerformed(GestureEvent e) {
				System.out.println(e.getTemplateName() + " - " + e.getScore());
			}
		};
		
		surf.getContainer().addGestureEventListener(gestureListener);
		
		//Add a picture on the frame
		MTPicture pict = new MTPicture("data/Bird.jpg");
		pict.addDiscreteEventListener(dListener);
		pict.addSRTEventListener(srtListener);
		pict.getObb().setPosition(Math.PI, pict.getWidth(), pict.getHeight(), new Vector2(250, 250));
		pict.getObb().updatePosition(new Vector2(0, 0), 0, 2);
		surf.add(pict);
		
		//Add a second picture on the frame
		MTPicture pict2 = new MTPicture("data/Yellow_Tulips.jpg");
		pict2.addDiscreteEventListener(dListener);
		pict2.addSRTEventListener(srtListener);
		pict2.getObb().setPosition(0, pict2.getWidth(), pict2.getHeight(), new Vector2(0, 0));
		surf.add(pict2);
	}
}
