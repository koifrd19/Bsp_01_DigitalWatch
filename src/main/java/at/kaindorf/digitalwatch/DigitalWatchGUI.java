package at.kaindorf.digitalwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DigitalWatchGUI extends JFrame {

    public DigitalWatchGUI(){
        initComponents();
    }

    private void initComponents(){
        setLayout(new GridLayout(3,2));
        setSize(1200, 400);
        JLabel localTime = new JLabel("Local Time: ");
        DigitPanel localPanel = new DigitPanel();
        localPanel.startonStart(0);
        this.add(localTime);
        this.add(localPanel);

        for (int i = 0; i < 2; i++) {
            JLabel jLabel = new JLabel("Enter Location");
            DigitPanel digitalPanel = new DigitPanel();
            this.add(jLabel);
            this.add(digitalPanel);

            jLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    onMausClicked(jLabel, digitalPanel);
                }
            });
        }


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void onMausClicked(JLabel location, DigitPanel digitPanel){

            String loc = JOptionPane.showInputDialog("Enter Location");
        int offsetHours = Integer.parseInt(JOptionPane.showInputDialog("Ente Offset in Hours"));

        if (loc == null) return;

        location.setText(loc);
        digitPanel.startonStart(offsetHours);
    }

    public static void main(String[] args) {
        new DigitalWatchGUI();
    }

}
