package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIFrame extends JFrame{
	
	private JButton exitButton;
	
	public GUIFrame() {
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,480);
		setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 600))/2;
		int y = (int) ((dimension.getHeight() - 400))/2;
		setLocation(x, y);
		setVisible(true);
		
	}
	
	public void addExitButton() {
		
		exitButton = new JButton("Exit");
		exitButton.setSize(100, 40);
		exitButton.setLocation(500,330);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		add(exitButton);
	}
}