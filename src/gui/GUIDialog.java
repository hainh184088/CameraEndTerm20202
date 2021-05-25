package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class GUIDialog extends JDialog {
	
	public JButton okJButton = new JButton("OK");
	
	public GUIDialog(Frame frame) {
		super(frame);
		setSize(600,400);
		setLayout(null);
		setResizable(false);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 600))/2;
		int y = (int) ((dimension.getHeight() - 400))/2;
		setLocation(x, y);
		
		setVisible(false);
		setModal(true);
		
		okJButton.setSize(100,30);
		okJButton.setLocation(250,330);
		okJButton.setFocusPainted(false);
		add(okJButton);
		
	}
	
	public void addOK(int width,int height) {
		okJButton.setSize(100,40);
		okJButton.setLocation(width,height);
		okJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		okJButton.setFocusPainted(false);
		add(okJButton);
	}
	
}
