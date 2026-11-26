
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cpu.GanttBlock;

public class GanttChart extends JPanel {

    private static final int UNIT = 30;     
    private static final int LEFT = 20;     
    private static final int TOP = 20;     
    private static final int HEIGHT = 40;   

    private List<GanttBlock> chart = new ArrayList<GanttBlock>();

    public GanttChart() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 100));
    }

    public void setChart(List<GanttBlock> chart) {
        this.chart = chart;
        int totalTime = chart.isEmpty() ? 0 : chart.get(chart.size() - 1).end;
        setPreferredSize(new Dimension(LEFT * 2 + totalTime * UNIT, 100));
        revalidate();   
        repaint();      
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (chart.isEmpty()) {
            g.drawString("Click Compute to show the Gantt chart.", LEFT, TOP + 20);
            return;
        }

        g.drawString("0", LEFT - 3, TOP + HEIGHT + 15);
        for (GanttBlock block : chart) {
            int x = LEFT + block.start * UNIT;
            int width = (block.end - block.start) * UNIT;

            
            if (block.name.equals("Idle")) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, TOP, width, HEIGHT);
            }

            
            g.setColor(Color.BLACK);
            g.drawRect(x, TOP, width, HEIGHT);
            int textWidth = g.getFontMetrics().stringWidth(block.name);
            g.drawString(block.name, x + (width - textWidth) / 2, TOP + HEIGHT / 2 + 5);
            g.drawString(String.valueOf(block.end), x + width - 5, TOP + HEIGHT + 15);
        }
    }
}
