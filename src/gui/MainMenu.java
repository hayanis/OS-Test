
package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cpu.FcfsScheduler;
import cpu.PriorityPreemptiveScheduler;
import disk.CLookScheduler;
import disk.CScanScheduler;

public class MainMenu extends JFrame {

    
    static final String SECTION = "[3ITH]";
    static final String GROUP_NUMBER = "[Group No. 8]";

    public MainMenu() {
        super("CPU and Disk Scheduling Simulator");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                exitProgram();
            }
        });

        
        JLabel title = new JLabel("CPU and Disk Scheduling Simulator", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLabel subtitle = new JLabel("ICS26012 - Operating Systems  |  " + SECTION + "  |  Group "
                + GROUP_NUMBER, SwingConstants.CENTER);
        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(title);
        top.add(subtitle);

       
        JButton fcfsButton = new JButton("First-Come, First-Served (FCFS)");
        JButton prioButton = new JButton("Priority Scheduling (Preemptive)");
        fcfsButton.addActionListener(e -> new CpuScreen(new FcfsScheduler()).setVisible(true));
        prioButton.addActionListener(e -> new CpuScreen(new PriorityPreemptiveScheduler()).setVisible(true));
        JPanel cpuGroup = new JPanel(new GridLayout(2, 1, 0, 10));
        cpuGroup.setBorder(BorderFactory.createTitledBorder("CPU Scheduling"));
        cpuGroup.add(fcfsButton);
        cpuGroup.add(prioButton);

        
        JButton cscanButton = new JButton("C-SCAN (Circular SCAN)");
        JButton clookButton = new JButton("C-LOOK (Circular LOOK)");
        cscanButton.addActionListener(e -> new DiskScreen(new CScanScheduler()).setVisible(true));
        clookButton.addActionListener(e -> new DiskScreen(new CLookScheduler()).setVisible(true));
        JPanel diskGroup = new JPanel(new GridLayout(2, 1, 0, 10));
        diskGroup.setBorder(BorderFactory.createTitledBorder("Disk Scheduling"));
        diskGroup.add(cscanButton);
        diskGroup.add(clookButton);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 0));
        center.add(cpuGroup);
        center.add(diskGroup);

       
        JButton aboutButton = new JButton("About");
        JButton exitButton = new JButton("Exit");
        aboutButton.addActionListener(e -> showAbout());
        exitButton.addActionListener(e -> exitProgram());
        JPanel bottom = new JPanel();
        bottom.add(aboutButton);
        bottom.add(exitButton);

        JPanel content = new JPanel(new BorderLayout(10, 20));
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        content.add(top, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(bottom, BorderLayout.SOUTH);
        setContentPane(content);

        setSize(700, 360);
        setLocationRelativeTo(null);   
    }

    private void showAbout() {
        String text = "CPU and Disk Scheduling Simulator\n"
                + "ICS26012 - Operating Systems\n"
                + "Section: " + SECTION + "     Group No.: " + GROUP_NUMBER + "\n\n"
                + "Members:\n"
                + "  Hyannis Amber D. Pascual  -  FCFS (CPU, non-preemptive)\n"
                + "  Emmanuel A. Larin    -  Priority Scheduling, Preemptive (CPU)\n"
                + "  Amiel Lauren B. Sabater    -  C-SCAN (Disk)\n"
                + "  James O. Larios   -  C-LOOK (Disk)\n\n"
                + "Written in Java with Swing.";
        JOptionPane.showMessageDialog(this, text, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitProgram() {
        int answer = JOptionPane.showConfirmDialog(this, "Exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
