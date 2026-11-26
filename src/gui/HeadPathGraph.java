
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class HeadPathGraph extends JPanel {

    private static final int LEFT = 40;   
    private static final int RIGHT = 40;  
    private static final int TOP = 40;    
    private static final int ROW = 25;    

    private List<Integer> path = new ArrayList<Integer>();
    private int diskSize = 200;
    private boolean towardHigher = true;

    public HeadPathGraph() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 200));
    }

    public void setPath(List<Integer> path, int diskSize, boolean towardHigher) {
        this.path = path;
        this.diskSize = diskSize;
        this.towardHigher = towardHigher;
        setPreferredSize(new Dimension(600, TOP + path.size() * ROW + 20));
        revalidate();
        repaint();
    }

    
    private int xOf(int cylinder) {
        int usableWidth = getWidth() - LEFT - RIGHT;
        return LEFT + cylinder * usableWidth / (diskSize - 1);
    }

    
    private int yOf(int step) {
        return TOP + 20 + step * ROW;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (path.isEmpty()) {
            g.drawString("Click Compute to show the head's path.", LEFT, TOP);
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        
        int last = diskSize - 1;
        g2.drawLine(xOf(0), TOP, xOf(last), TOP);
        int[] labels = {0, last / 2, last};
        for (int c : labels) {
            g2.drawLine(xOf(c), TOP - 4, xOf(c), TOP + 4);
            g2.drawString(String.valueOf(c), xOf(c) - 8, TOP - 8);
        }

       
        BasicStroke solid = new BasicStroke(2);
        BasicStroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{6, 6}, 0);
        for (int i = 1; i < path.size(); i++) {
            int from = path.get(i - 1);
            int to = path.get(i);
            boolean isJump = towardHigher ? to < from : to > from;
            g2.setStroke(isJump ? dashed : solid);
            g2.drawLine(xOf(from), yOf(i - 1), xOf(to), yOf(i));
        }

        
        g2.setStroke(new BasicStroke(1));
        for (int i = 0; i < path.size(); i++) {
            int x = xOf(path.get(i));
            int y = yOf(i);
            g2.fillOval(x - 4, y - 4, 8, 8);
            g2.drawString(String.valueOf(path.get(i)), x + 8, y + 5);
        }
    }
}
