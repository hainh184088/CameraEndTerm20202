package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Camera.Camera;
import Object.Object;
import Point.Point;
import ReadFile.ReadFile;
import Room.Room;

public class HomeFrame extends GUIFrame {	
	
	private JButton createRoomButton = new JButton("Lập phòng");
	private JButton addObject = new JButton("Thêm vật thể trong phòng");
	private JButton addCamera = new JButton("Thêm camera");
	private JButton calcHidden = new JButton("Tính toán vùng khuất");
	private JButton displayHidden = new JButton("Hiển thị vùng khuất");

	Room room = new Room();
	ArrayList<ArrayList<ArrayList<Integer>>> coordinates = new ArrayList<ArrayList<ArrayList<Integer>>>();
	double step = 1;
    double stepTimes = 1 / step;
	
	public HomeFrame() {
		super();
		
		int reply = JOptionPane.showConfirmDialog(null, "Import file?", null, JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION) {
			try {
				ReadFile.readFile(room);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		

		setTitle("Camera Project");
		
		createRoomButton.setSize(400, 40);
		createRoomButton.setLocation(100, 30);
		createRoomButton.setFocusPainted(false);
		createRoomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				GUIDialog createRoomDialog = new GUIDialog(null);
				createRoomDialog.setTitle("Lập phòng");
				
				JLabel lengthLabel = new JLabel("Length:");
				JLabel widthLabel = new JLabel("Width:");
				JLabel heightLabel = new JLabel("Height:");
									
				JTextField lengthField = new JTextField();
				JTextField widthField = new JTextField();
				JTextField heightField = new JTextField();
				
				lengthLabel.setSize(200,40);
				lengthLabel.setLocation(150,20);
				createRoomDialog.add(lengthLabel);
				lengthField.setSize(200,30);
				lengthField.setLocation(300,20);
				createRoomDialog.add(lengthField);
				
				widthLabel.setSize(200,40);
				widthLabel.setLocation(150,100);
				createRoomDialog.add(widthLabel);
				widthField.setSize(200,30);
				widthField.setLocation(300,100);
				createRoomDialog.add(widthField);
				
				heightLabel.setSize(200,40);
				heightLabel.setLocation(150,180);
				createRoomDialog.add(heightLabel);
				heightField.setSize(200,30);
				heightField.setLocation(300,180);
				createRoomDialog.add(heightField);
				
				createRoomDialog.okJButton.addActionListener(new ActionListener() {
						
					@Override
					public void actionPerformed(ActionEvent e) {
						createRoomDialog.setVisible(false);
						if (lengthField.getText().equals("") || widthField.getText().equals("") || heightField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Chưa đủ thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else if (Integer.parseInt(lengthField.getText()) == 0 || Integer.parseInt(widthField.getText()) == 0 || Integer.parseInt(heightField.getText()) == 0) {
							JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else {
							room.setRoomLength(Integer.parseInt(lengthField.getText()));
		                    room.setRoomWidth(Integer.parseInt(widthField.getText()));
		                    room.setRoomHeight(Integer.parseInt(heightField.getText()));
		                    JOptionPane.showMessageDialog(null, "Lập phòng thành công");
						}
					}
					
				});
				
				createRoomDialog.setVisible(true);
					
				}
				
	    });
		
		add(createRoomButton);
		
		addObject.setSize(400, 40);
	    addObject.setLocation(100, 100);
	    addObject.setFocusPainted(false);
	    addObject.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (room.getRoomHeight() == 0 || room.getRoomLength() == 0 || room.getRoomWidth() == 0) {
	    			JOptionPane.showMessageDialog(null, "Chưa lập phòng", "Warning", JOptionPane.WARNING_MESSAGE);
	    		}
	    		else {
	    			GUIDialog addObjectDialog = new GUIDialog(null);    		
	    			addObjectDialog.setTitle("Thêm vật thể trong phòng");
	    		
	    			JLabel x1Label = new JLabel("Tọa độ x:");
	    			JLabel y1Label = new JLabel("Tọa độ y:");
	    			JLabel z1Label = new JLabel("Tọa độ z:");
	    			JLabel x2Label = new JLabel("Tọa độ x:");
	    			JLabel y2Label = new JLabel("Tọa độ y:");
	    			JLabel z2Label = new JLabel("Tọa độ z:");
	    			JLabel objectLengthLabel = new JLabel("Chiều dài:");
	    			JLabel objectHeightLabel = new JLabel("Chiều cao:");
									
	    			JTextField x1Field = new JTextField();
					JTextField y1Field = new JTextField();
					JTextField z1Field = new JTextField();
					JTextField x2Field = new JTextField();
					JTextField y2Field = new JTextField();
					JTextField z2Field = new JTextField();
					JTextField objectLengthField = new JTextField();
					JTextField objectHeightField = new JTextField();
					
					JLabel pointALabel = new JLabel("Điểm A:");
					pointALabel.setSize(200, 10);
					pointALabel.setLocation(50, 20);
					addObjectDialog.add(pointALabel);
					
					x1Label.setSize(200,40);
					x1Label.setLocation(150,20);
					addObjectDialog.add(x1Label);
					x1Field.setSize(200,30);
					x1Field.setLocation(300,20);
					addObjectDialog.add(x1Field);
					
					y1Label.setSize(200,40);
					y1Label.setLocation(150,60);
					addObjectDialog.add(y1Label);
					y1Field.setSize(200,30);
					y1Field.setLocation(300,60);
					addObjectDialog.add(y1Field);
					
					z1Label.setSize(200,40);
					z1Label.setLocation(150,100);
					addObjectDialog.add(z1Label);
					z1Field.setSize(200,30);
					z1Field.setLocation(300,100);
					addObjectDialog.add(z1Field);
					
					JLabel pointBLabel = new JLabel("Điểm B:");
					pointBLabel.setSize(200, 10);
					pointBLabel.setLocation(50, 140);
					addObjectDialog.add(pointBLabel);
					
					x2Label.setSize(200,40);
					x2Label.setLocation(150,140);
					addObjectDialog.add(x2Label);
					x2Field.setSize(200, 30);
					x2Field.setLocation(300,140);
					addObjectDialog.add(x2Field);
					
					y2Label.setSize(200,40);
					y2Label.setLocation(150,180);
					addObjectDialog.add(y2Label);
					y2Field.setSize(200, 30);
					y2Field.setLocation(300,180);
					addObjectDialog.add(y2Field);
					
					z2Label.setSize(200,40);
					z2Label.setLocation(150,220);
					addObjectDialog.add(z2Label);
					z2Field.setSize(200,30);
					z2Field.setLocation(300,220);
					addObjectDialog.add(z2Field);
					
					objectLengthLabel.setSize(200,40);
					objectLengthLabel.setLocation(150,260);
					addObjectDialog.add(objectLengthLabel);
					objectLengthField.setSize(200,30);
					objectLengthField.setLocation(300,260);
					addObjectDialog.add(objectLengthField);
					
					objectHeightLabel.setSize(200,40);
					objectHeightLabel.setLocation(150,300);
					addObjectDialog.add(objectHeightLabel);
					objectHeightField.setSize(200,30);
					objectHeightField.setLocation(300,300);
					addObjectDialog.add(objectHeightField);
					
					addObjectDialog.okJButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							addObjectDialog.setVisible(false);
							if (x1Field.getText().equals("") || y1Field.getText().equals("") || z1Field.getText().equals("") || x2Field.getText().equals("") || y2Field.getText().equals("") || z2Field.getText().equals("") || objectLengthField.getText().equals("") || objectHeightField.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Chưa đủ thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
							}
							else {							
								Object newObject = new Object(
										Integer.parseInt(x1Field.getText()), 
										Integer.parseInt(y1Field.getText()), 
										Integer.parseInt(z1Field.getText()), 
										Integer.parseInt(x2Field.getText()), 
										Integer.parseInt(y2Field.getText()), 
										Integer.parseInt(z2Field.getText()), 
										Integer.parseInt(objectLengthField.getText()), 
										Integer.parseInt(objectHeightField.getText()));	
								
								room.addObject(newObject);
								if (room.checkObjectValidity(newObject) == 0) {
									JOptionPane.showMessageDialog(null, "Vật thể không nằm trong phòng hoặc một phần của vật thể ở ngoài phòng", "Warning", JOptionPane.WARNING_MESSAGE);
	                        }
								else {									
									JOptionPane.showMessageDialog(null, "Thêm vật thành công");
								}
							}
						}
					});
					
					addObjectDialog.setVisible(true);					
	    		}
	    	}
	    	
	    });	    
	    
	    add(addObject);
	    
	    addCamera.setSize(400, 40);
	    addCamera.setLocation(100, 170);
	    addCamera.setFocusPainted(false);
	    addCamera.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (room.getRoomHeight() == 0 || room.getRoomLength() == 0 || room.getRoomWidth() == 0) {
	    			JOptionPane.showMessageDialog(null, "Chưa lập phòng", "Warning", JOptionPane.WARNING_MESSAGE);
	    		}
	    		else {
	    			
	    			GUIDialog addCameraDialog = new GUIDialog(null);
	    			addCameraDialog.setTitle("Thêm Camera");
	    			
	    			JLabel xCamLabel = new JLabel("Tọa độ x:");
	    			JLabel yCamLabel = new JLabel("Tọa độ y:");
	    			JLabel zCamLabel = new JLabel("Tọa độ z:");
	    			JLabel angleWidthLabel = new JLabel("Góc rộng");
	    			JLabel angleHeightLabel = new JLabel("Góc cao");
	    			JLabel rangeLabel = new JLabel("Tầm nhìn");
	    			
	    			JTextField xCamField = new JTextField();
	    			JTextField yCamField = new JTextField();
	    			JTextField zCamField = new JTextField();
	    			JTextField angleWidthField = new JTextField();
	    			JTextField angleHeightField = new JTextField();
	    			JTextField rangeField = new JTextField();
	    			
	    			xCamLabel.setSize(200,40);
	    			xCamLabel.setLocation(150,20);
	    			addCameraDialog.add(xCamLabel);
	    			xCamField.setSize(200,30);
	    			xCamField.setLocation(300,20);
	    			addCameraDialog.add(xCamField);
	    			
	    			yCamLabel.setSize(200,40);
	    			yCamLabel.setLocation(150,70);
	    			addCameraDialog.add(yCamLabel);
	    			yCamField.setSize(200,30);
	    			yCamField.setLocation(300,70);
	    			addCameraDialog.add(yCamField);
	    			
	    			zCamLabel.setSize(200,40);
	    			zCamLabel.setLocation(150,120);
	    			addCameraDialog.add(zCamLabel);
	    			zCamField.setSize(200,30);
	    			zCamField.setLocation(300,120);
	    			addCameraDialog.add(zCamField);
	    			
	    			angleWidthLabel.setSize(200,40);
	    			angleWidthLabel.setLocation(150,170);
	    			addCameraDialog.add(angleWidthLabel);
	    			angleWidthField.setSize(200,30);
	    			angleWidthField.setLocation(300,170);
	    			addCameraDialog.add(angleWidthField);
	    			
	    			angleHeightLabel.setSize(200,40);
	    			angleHeightLabel.setLocation(150,220);
	    			addCameraDialog.add(angleHeightLabel);
	    			angleHeightField.setSize(200,30);
	    			angleHeightField.setLocation(300,220);
	    			addCameraDialog.add(angleHeightField);
	    			
	    			rangeLabel.setSize(200,40);
	    			rangeLabel.setLocation(150,270);
	    			addCameraDialog.add(rangeLabel);
	    			rangeField.setSize(200,30);
	    			rangeField.setLocation(300,270);
	    			addCameraDialog.add(rangeField);
	    			
	    			addCameraDialog.okJButton.addActionListener(new ActionListener() {
	    				
	    				@Override
	    				public void actionPerformed(ActionEvent e) {
	    					addCameraDialog.setVisible(false);
	    					if (xCamField.getText().equals("") || yCamField.getText().equals("") || zCamField.getText().equals("") || angleWidthField.getText().equals("") || angleHeightField.getText().equals("") || rangeField.getText().equals("")) {
	    						JOptionPane.showMessageDialog(null, "Chưa đủ thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
	    					}
	    					else {
	    						Camera newCamera = new Camera(
	    								Integer.parseInt(xCamField.getText()), 
										Integer.parseInt(yCamField.getText()), 
										Integer.parseInt(zCamField.getText()), 
										Integer.parseInt(angleWidthField.getText()), 
										Integer.parseInt(angleHeightField.getText()), 
										Integer.parseInt(rangeField.getText()));
	    						
	    						if (room.checkCameraPosition(newCamera) == 0) {
	    							JOptionPane.showMessageDialog(null, "Camera không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
	    						}
	    						else {
	    							room.addCamera(newCamera);
	    							JOptionPane.showMessageDialog(null, "Thêm camera thành công");
	    						}
	    					}
	    				}
	    		
	    			});
				
	    			addCameraDialog.setVisible(true);
	    		}
	    	}
	    	
	    });
	    
	    add(addCamera);
	    
	    calcHidden.setSize(400, 40);
	    calcHidden.setLocation(100, 240);
	    calcHidden.setFocusPainted(false);
	    calcHidden.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (room.getRoomHeight() == 0 || room.getRoomLength() == 0 || room.getRoomWidth() == 0) {
	    			JOptionPane.showMessageDialog(null, "Chưa lập phòng", "Warning", JOptionPane.WARNING_MESSAGE);
	    		}
	    		else {
	    			
	    			GUIDialog calcHiddenDialog = new GUIDialog(null);
	    			calcHiddenDialog.setTitle("Tính toán vùng khuất");
	    			
	    			JLabel xLabel = new JLabel("Tọa độ x:");
	    			JLabel yLabel = new JLabel("Tọa độ y:");
	    			JLabel zLabel = new JLabel("Tọa độ z:");
	    			
	    			JTextField xField = new JTextField();
	    			JTextField yField = new JTextField();
	    			JTextField zField = new JTextField();
	    			
	    			xLabel.setSize(200,40);
					xLabel.setLocation(150,20);
					calcHiddenDialog.add(xLabel);
					xField.setSize(200,30);
					xField.setLocation(300,20);
					calcHiddenDialog.add(xField);
					
					yLabel.setSize(200,40);
					yLabel.setLocation(150,100);
					calcHiddenDialog.add(yLabel);
					yField.setSize(200,30);
					yField.setLocation(300,100);
					calcHiddenDialog.add(yField);
					
					zLabel.setSize(200,40);
					zLabel.setLocation(150,180);
					calcHiddenDialog.add(zLabel);
					zField.setSize(200,30);
					zField.setLocation(300,180);
					calcHiddenDialog.add(zField);
					
					calcHiddenDialog.okJButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							calcHiddenDialog.setVisible(false);
							if (xField.getText().equals("") || yField.getText().equals("") || zField.getText().equals("")) {
	    						JOptionPane.showMessageDialog(null, "Chưa đủ thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
	    					}
							else {
								Point testPoint = new Point(
										Integer.parseInt(xField.getText()), 
										Integer.parseInt(yField.getText()), 
										Integer.parseInt(zField.getText()));
								
			                    testPoint.checkPointStatus(room);
			                    
			                    if (testPoint.getInsideObject() == 1) {
			                    	JOptionPane.showMessageDialog(null, "Điểm nằm trong vật thể");
			                    }
			                    else if (testPoint.getCovered() == 1) {
			                    	JOptionPane.showMessageDialog(null, "Điểm bị chắn bởi vật thể");
			                    }
			                    else if (testPoint.getSeen() == 1) {
			                    	JOptionPane.showMessageDialog(null, "Điểm nằm trong vùng nhìn được của camera");
			                    }
			                    else {
			                    	JOptionPane.showMessageDialog(null, "Điểm không bị chắn bởi vật nào nhưng không có camera chiếu vào");
			                    }
							}							
						}						
					});
					
					calcHiddenDialog.setVisible(true);
	    		}
	    	}	
	    });
	    
	    add(calcHidden);
	    
	    displayHidden.setSize(400, 40);
	    displayHidden.setLocation(100, 310);
	    displayHidden.setFocusPainted(false);
	    displayHidden.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (room.getRoomHeight() == 0 || room.getRoomLength() == 0 || room.getRoomWidth() == 0) {
	    			JOptionPane.showMessageDialog(null, "Chưa lập phòng", "Warning", JOptionPane.WARNING_MESSAGE);
	    		}
	    		else {
	    			ArrayList<ArrayList<Integer>> status1 = new ArrayList<ArrayList<Integer>>();
	    			ArrayList<ArrayList<Integer>> status2 = new ArrayList<ArrayList<Integer>>();
	    			ArrayList<ArrayList<Integer>> status3 = new ArrayList<ArrayList<Integer>>();
	    			ArrayList<ArrayList<Integer>> status4 = new ArrayList<ArrayList<Integer>>();
	    			ArrayList<ArrayList<Integer>> status5 = new ArrayList<ArrayList<Integer>>();
	    			Point newPoint = new Point(0, 0, 0);
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                        coordinates.add(new ArrayList<ArrayList<Integer>>(room.getRoomWidth()));
                        for (int j = 0; j <= room.getRoomWidth(); j += step) {
                            coordinates.get(k).add(new ArrayList<Integer>(room.getRoomLength()));
                            for (int i = 0; i <= room.getRoomLength(); i += step) {
                                newPoint.setX(i*stepTimes);
                                newPoint.setY(j*stepTimes);
                                newPoint.setZ(k*stepTimes);
                                newPoint.setCovered(0);
                                newPoint.setSeen(0);
                                newPoint.setInsideObject(0);
                                newPoint.checkPointStatus(room);
                                coordinates.get((int) (k*stepTimes)).get((int) (j*stepTimes)).add((int) (i*stepTimes), newPoint.setPointStatus());
                            }
                        }
                    }
                    //surface vertical with x = 0
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                    	status1.add(new ArrayList<Integer>(room.getRoomHeight()));
                        for (int j = 0; j <= room.getRoomWidth(); j += step) {
                        	//status2[k][j] = coordinates.get((int) (k*stepTimes)).get((int) (j*stepTimes)).get(0);
                        	status1.get((int) (k*stepTimes)).add((int) (j*stepTimes), coordinates.get((int) ((room.getRoomHeight()-k)*stepTimes)).get((int) (j*stepTimes)).get(0));
                        }
                    }
                    //surface vertical with x = roomLength
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                    	status2.add(new ArrayList<Integer>(room.getRoomHeight()));
                        for (int j = 0; j <= room.getRoomWidth(); j += step) {
                        	status2.get((int) (k*stepTimes)).add((int) (j*stepTimes), coordinates.get((int) ((room.getRoomHeight()-k)*stepTimes)).get((int) ((room.getRoomHeight()-j)*stepTimes)).get(room.getRoomLength()));
                        	//status2[k][j] = coordinates.get((int) (k*stepTimes)).get((int) (j*stepTimes)).get(room.getRoomLength());
                        }
                    }
                    //surface vertical with y = 0
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                    	status3.add(new ArrayList<Integer>(room.getRoomHeight()));
                        for (int i = 0; i <= room.getRoomLength(); i += step) {
                        	status3.get((int) (k*stepTimes)).add((int) (i*stepTimes), coordinates.get((int) ((room.getRoomHeight()-k)*stepTimes)).get(0).get((int) ((room.getRoomLength()-i)*stepTimes)));
                        	//status3[k][i] = coordinates.get((int) (k*stepTimes)).get(0).get((int) (i*stepTimes));
                        }
                    }
                    //surface vertical with y = roomWidth
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                    	status4.add(new ArrayList<Integer>(room.getRoomHeight()));
                        for (int i = 0; i <= room.getRoomLength(); i += step) {
                        	status4.get((int) (k*stepTimes)).add((int) (i*stepTimes), coordinates.get((int) ((room.getRoomHeight()-k)*stepTimes)).get(room.getRoomWidth()).get((int) (i*stepTimes)));
                        	//status4[k][i] = coordinates.get((int) (k*stepTimes)).get(room.getRoomWidth()).get((int) (i*stepTimes));
                        }
                    }
                    //surface horizontal with k = 0
                    for (int j = 0; j <= room.getRoomWidth(); j += step) {
                    	status5.add(new ArrayList<Integer>(room.getRoomWidth()));
                        for (int i = 0; i <= room.getRoomLength(); i += step) {
                        	status5.get((int) (j*stepTimes)).add((int) (i*stepTimes), coordinates.get(0).get((int) (j*stepTimes)).get((int) (i*stepTimes)));
                        	//status5[j][i] = coordinates.get(room.getRoomHeight()).get((int) (j*stepTimes)).get((int) (i*stepTimes));                            
                        }
                    }
                    
                    
	    			GUIDialog displayHiddenDialog= new GUIDialog(null);
	    			displayHiddenDialog.setTitle("Hiển thị vùng khuất");
	    			displayHiddenDialog.setSize(1240,820);
	    			//remove(displayHiddenDialog.okJButton);
	    			//displayHiddenDialog.okJButton.setSize(100,30);
	    			//displayHiddenDialog.okJButton.setLocation(450,400);
	    			//displayHiddenDialog.okJButton.setFocusPainted(false);
	    			add(displayHiddenDialog.okJButton); // ???
	    				    			
	    			JLabel vertX0 = new JLabel("surface vertical with x = 0 (trái sang phải)");
	    			JLabel vertXL = new JLabel("surface vertical with x = room length (phải sang trái)");
	    			JLabel vertY0 = new JLabel("surface vertical with y = 0 (sau sang trước)");
	    			JLabel vertYW = new JLabel("surface vertical with y = room width (trước sang sau)");
	    			JLabel horiZ0 = new JLabel("surface horizontal with k = 0 (trên xuống dưới)");
	    			
	    			vertX0.setSize(400,40);
	    			vertX0.setLocation(10,5);
	    			displayHiddenDialog.add(vertX0);
	    			vertXL.setSize(400,40);
	    			vertXL.setLocation(10,415);
	    			displayHiddenDialog.add(vertXL);
	    			vertY0.setSize(400,40);
	    			vertY0.setLocation(420,5);
	    			displayHiddenDialog.add(vertY0);
	    			vertYW.setSize(400,40);
	    			vertYW.setLocation(420,415);
	    			displayHiddenDialog.add(vertYW);
	    			horiZ0.setSize(400,40);
	    			horiZ0.setLocation(830,100);
	    			displayHiddenDialog.add(horiZ0);
	    			
	    			GUIPanel panel1 = new GUIPanel(status1);
	    			GUIPanel panel2 = new GUIPanel(status2);
	    			GUIPanel panel3 = new GUIPanel(status3);
	    			GUIPanel panel4 = new GUIPanel(status4);
	    			GUIPanel panel5 = new GUIPanel(status5);
	    			
	    			panel1.setLocation(10,40);
	    			panel1.setSize(400,400);
	    			panel2.setLocation(10,450);
	    			panel2.setSize(400,400);
	    			panel3.setLocation(420,40);
	    			panel3.setSize(400,400);
	    			panel4.setLocation(420,450);
	    			panel4.setSize(400,400);
	    			panel5.setLocation(830,140);
	    			panel5.setSize(400,400);
	    			
	    			displayHiddenDialog.add(panel1);
	    			displayHiddenDialog.add(panel2);
	    			displayHiddenDialog.add(panel3);
	    			displayHiddenDialog.add(panel4);
	    			displayHiddenDialog.add(panel5);
	    			
	    			displayHiddenDialog.setVisible(true);
	    		}
	    			
	    	}
	    		
	    });
	    
	    add(displayHidden);
	    
	 }
	 
}
