package at.kaindorf.digitalwatch;

import javax.swing.*;
import java.awt.*;

public class DigitLabel extends JLabel {
    private int value = -1;

    private static int [][] segmentForValue = {
            {0,1,2,3,4,5},  //0
            {1,2},          //1
            {0,1,3,4,6},    //2
            {0,1,2,3,6},    //3
            {1,2,5,6},      //4
            {0,5,6,2,3},    //5
            {0,5,6,2,3,4},  //6
            {0,1,2},        //7
            {0,1,2,3,4,5,6},//8
            {0,1,2,5,6},    //

    };

    public DigitLabel(){
        setBackground(new Color(0, 0, 0));
        setOpaque(true);
    }

    public int getValue() {
        return value;
    }

    public static void setSegmentForValue(int[][] segmentForValue) {
        DigitLabel.segmentForValue = segmentForValue;
    }

    public void setValue(int value) {
        this.value = value;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;

         RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
         g2.setRenderingHints(rh);

//        g2.setPaint(Color.BLACK);
//        g2.fillOval(100,150,80,80);
//        g2.setStroke(new BasicStroke(3.5f));
//
//        g2.setPaint(Color.YELLOW);
//        g2.drawOval(100,150,80,80);

        g2.scale(this.getWidth()/11.0,getHeight()/18.0);
        if (getValue() == -1){
            g2.setPaint(Color.RED);
            g2.fillRect(5,5,1,2);

            g2.setStroke(new BasicStroke(0.2f));
            g2.setPaint(Color.BLACK);
            g2.drawRect(5,5,1,2);

            g2.setPaint(Color.RED);
            g2.fillRect(5,10,1,2);

            g2.setStroke(new BasicStroke(0.2f));
            g2.setPaint(Color.BLACK);
            g2.drawRect(5,10,1,2);
        }else
        for (int i = 0; i < segmentForValue[getValue()].length; i++) {
            System.out.println("painted Value: "+ getValue());

            Polygon polygon = new Polygon(getXCoordsOfSegment(segmentForValue[getValue()][i]),getYCoordsOfSegment(segmentForValue[getValue()][i])
                    ,getXCoordsOfSegment(segmentForValue[getValue()][i]).length);
//
//            System.out.println("Data of " +i +". Polygon: ");
//            System.out.println("xCoords:" +getXCoordsOfSegment(segmentForValue[getValue()][i]).toString());
//            System.out.println("yCoords: " +getYCoordsOfSegment(segmentForValue[getValue()][i]).toString());
//            System.out.println("Length " + getXCoordsOfSegment(segmentForValue[getValue()][i]).length);

            g2.setPaint(Color.RED);
            g2.fillPolygon(polygon);

            g2.setStroke(new BasicStroke(0.2f));
            g2.setPaint(Color.BLACK);
            g2.drawPolygon(polygon);
        }
    }

    private static int[] getXCoordsOfSegment(int segment){
        return switch (segment){
            case 0, 3, 6 -> new int[] {2, 3, 8, 9, 8, 3};
            case 1, 2 -> new int[] {9, 10, 10, 9, 8 ,8};
            case 4, 5 -> new int[] {2, 3, 3, 2, 1, 1};
            default -> new int[]{};
        };
    }

    private static int[] getYCoordsOfSegment(int segment){
        return switch (segment){
            case 0 -> new int[] {2, 1, 1, 2, 3, 3};
            case 1, 5 -> new int[] {2, 3, 8, 9, 8, 3};
            case 6 -> new int[] {9, 8, 8, 9, 10, 10};
            case 2, 4 -> new int[] {9, 10, 15, 16, 15, 10};
            case 3 -> new int[] {16, 15, 15, 16, 17, 17};
            default -> new int[]{};
        };
    }

    public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.setSize(200,400);
            DigitLabel dLabel = new DigitLabel();
            frame.getContentPane().add(dLabel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
}

//for (int i = 0; i <= 10; i++) {
//                dLabel.setValue(i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

