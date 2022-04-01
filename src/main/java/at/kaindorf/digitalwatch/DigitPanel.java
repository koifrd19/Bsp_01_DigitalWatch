package at.kaindorf.digitalwatch;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DigitPanel extends JPanel implements Runnable {

    private DigitLabel[] digitLabels = new DigitLabel[8];
    private int timeOffsetInHours = 0;
    private Thread digitThread;

    public DigitPanel(){
        initComponents();

    }

    public void startonStart(int timeOffsetInHours){
//        Auf die Plätze. Fertig? LOS!
        this.timeOffsetInHours = timeOffsetInHours;
        digitThread= new Thread(this);
        digitThread.start();
    }

    public void initComponents(){
        setLayout(new GridLayout(1,8));
        for (int i = 0; i < digitLabels.length; i++) {
            digitLabels[i] = new DigitLabel();
            add(digitLabels[i]);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            setLabels();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void setLabels(){
        LocalTime lTime = LocalTime.now().plusHours(timeOffsetInHours);
        lTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//        System.out.println("Time: " + lTime);
        String time = "";

        if (lTime.getHour()< 10){
            time += "0";
        }
        time += String.format("%d", lTime.getHour());

        if (lTime.getMinute() < 10){
            time += "0";
        }
        time +=String.format("%d", lTime.getMinute());

        if (lTime.getSecond() < 10){
            time += "0";
        }
        time += String.format("%d", lTime.getSecond());


        digitLabels[0].setValue(Integer.parseInt(String.format("%c",time.charAt(0))));
        digitLabels[1].setValue(Integer.parseInt(String.format("%c",time.charAt(1))));
        digitLabels[3].setValue(Integer.parseInt(String.format("%c",time.charAt(2))));
        digitLabels[4].setValue(Integer.parseInt(String.format("%c",time.charAt(3))));
        digitLabels[6].setValue(Integer.parseInt(String.format("%c",time.charAt(4))));
        digitLabels[7].setValue(Integer.parseInt(String.format("%c",time.charAt(5))));

        System.out.println(digitLabels[0].getValue());
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800,200);
        jFrame.setLocationRelativeTo(null);
        DigitPanel dPanel = new DigitPanel();
        jFrame.add(dPanel);
        jFrame.setVisible(true);
        dPanel.setLabels();


    }
}
