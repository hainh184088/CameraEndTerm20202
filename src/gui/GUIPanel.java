package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GUIPanel extends JPanel {

    ArrayList<ArrayList<Integer>> status = new ArrayList<ArrayList<Integer>>();
    
    private int size = 5;
    
    public GUIPanel(ArrayList<ArrayList<Integer>> status)
    {
        super();
//        setSize(250,100);
//		setLayout(null);
//		//setResizable(false);
//		
//		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (int) ((dimension.getWidth() - 300))/2;
//		int y = (int) ((dimension.getHeight() - 200))/2;
//		setLocation(x, y);
//		
//		setVisible(false);
		//setModal(true);
        this.status = status;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int j = 0; j < status.get(0).size(); j++)
        {
            for (int i = 0; i < status.size(); i++)
            {
                if (status.get(j).get(i) == 1)
                    g.setColor(Color.GRAY);
                else
                    g.setColor(Color.WHITE);
                g.fillRect(i * size, j * size, size, size);
            }
        }
        
    }
    
}
