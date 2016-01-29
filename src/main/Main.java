package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

import gui.Display;
import gui.SBDEFrame;

public class Main {
	
	public static void main(String []args){
		new SBDEFrame();
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				SBDEFrame frame = new SBDEFrame();
				//setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setSize(400, 400);
				frame.setLayout(new BorderLayout());
				frame.add(new Display(), BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}

}
