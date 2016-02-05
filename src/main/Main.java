package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

import gui.Display;
import gui.SBDEFrame;

public class Main {
	
	public static void main(String []args){
		Constants.init();
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				SBDEFrame frame = new SBDEFrame();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setSize(900, 400);
				frame.setLayout(new BorderLayout());
				frame.add(Display.getInstance(), BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}

}
