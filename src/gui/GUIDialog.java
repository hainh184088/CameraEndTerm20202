package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JTextField;

public class GUIDialog extends JDialog {
	
//	private JLabel xJLabel = new JLabel("Tọa độ x:");
//	private JLabel yJLabel = new JLabel("Tọa độ y:");
//	private JLabel zJLabel = new JLabel("Tọa độ z:");
//	
//	private JTextField xField = new JTextField();
//	private JTextField yField = new JTextField();
//	private JTextField zField = new JTextField();
//	
//	public int xGet() {
//		return Integer.parseInt(xField.getText());
//	}
//	
//	public int yGet() {
//		return Integer.parseInt(yField.getText());
//	}
//	
//	public int zGet() {
//		return Integer.parseInt(zField.getText());
//	}
	
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
	
//	public GUIDialog(Frame frame, String title) {
//		super(frame,title);
//		setSize(600,400);
//		setLayout(null);
//		setResizable(false);
//		
//		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (int) ((dimension.getWidth() - 600))/2;
//		int y = (int) ((dimension.getHeight() - 400))/2;
//		setLocation(x, y);
//		
//		xJLabel.setSize(200,30);
//		xJLabel.setLocation(100,20);
//		add(xJLabel);
//		xField.setSize(200, 30);
//		xField.setLocation(300,20);
//		add(xField);
//		
//		yJLabel.setSize(200,40);
//		yJLabel.setLocation(100,60);
//		add(yJLabel);
//		yField.setSize(200, 30);
//		yField.setLocation(300,60);
//		add(yField);
//		
//		zJLabel.setSize(200,40);
//		zJLabel.setLocation(100,100);
//		add(zJLabel);
//		zField.setSize(200, 30);
//		zField.setLocation(300,100);
//		add(zField);
//		
//		
//		okJButton.setSize(100,30);
//		okJButton.setLocation(250,330);
//		okJButton.setFocusPainted(false);
//		add(okJButton);
//		
//		
//		setVisible(false);
//		setModal(true);
//	}
	
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
	
//	public boolean isEmpty() {
//		return xField.getText().equals("") || yField.getText().equals("") || zField.getText().equals("");
//	}
	
}
