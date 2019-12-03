/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlosimulation;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author acer
 */
public class MonteCarloSimulation {

    public static int interval=0, i;
    public static double rand_x, rand_y, origin_dist, pi;
    public static int circle_points = 0, square_points = 0;
    public static int [] no_of_points=new int[10000000];
    public static double [] values=new double[10000000];
    public static int p=0,v=0;
    public static int interval2=0;
    public static double rand_z,origin_dist2, pi2;
    public static int circle_points2 = 0, square_points2 = 0;
    public static int [] no_of_points2=new int[10000000];
    public static double [] values2=new double[10000000];
    public static int p2=0,v2=0;
    public static double rand_num;
    public static int pos;
    public static int [] position=new int[1000];
    public static double [] random_num_array=new double[10000000];
    public static int p22=0,v22=0; 
    public static int r=1;
  
    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        int c2=0;
        for (i = 0; i < 1000000; i++) {
            rand_x = Math.random();
            rand_y = Math.random();
            interval++;
            no_of_points[p]=interval;
            p++;
            origin_dist = rand_x * rand_x + rand_y * rand_y;
            
            if (origin_dist <= 1) {
                c2++;
            }
           
            square_points++;
            
           
        }
         pi = Double.valueOf(4* c2) / square_points;
          System.out.println("pi"+pi);
         double areac1=pi*10*10;
         double areac2=pi*5*5;
         double a11=areac1-areac2;
         
         double a1=a11/12;
         double a2=a11/6;
         
         System.out.println("A1: "+a1);
         System.out.println("A2: "+a2);
          System.out.println("area ratio:"+Double.valueOf(a1/a2));
         
        
     /*   System.out.println();
        System.out.println("Final Estimation of Pi in 2D space is = " + pi);
        final XYSeries series = new XYSeries("");
         final XYSeries series2 = new XYSeries("");
        for (int j = 0; j < 1000; j++) {
            double g=values[j]-Double.valueOf(3.14);
            //System.out.println("Value "+values[j]+"   "+"error"+" "+g);
            
           series.add(no_of_points[j],Math.log(values[j]));
            series2.add(no_of_points[j],g);
            
        }
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final XYSeriesCollection data2 = new XYSeriesCollection(series2);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                
                "2D log(value) VS No of points",
                
                "No of points",
                "log(value)",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final JFreeChart chart2 = ChartFactory.createXYLineChart(
                "2D Error VS No of points",
                "No of points",
                "Error",
                data2,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        JFrame frame = new JFrame();
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        final ChartPanel chartPanel2 = new ChartPanel(chart2);
        

        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JPanel jpanel=new JPanel();
        jpanel.setBackground(Color.WHITE);
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
        jpanel.add(chartPanel);
        jpanel.add(chartPanel2);
        frame.add(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setVisible(true);
         for (i = 0; i < 1000000; i++) {
            rand_x = Math.random();
            rand_y = Math.random();
            rand_z = Math.random();
            interval2++;
            no_of_points2[p2]=interval2;
            p2++;
            origin_dist2 = rand_x * rand_x + rand_y * rand_y+rand_z * rand_z;
            
            if (origin_dist2 <= r*r) {
                circle_points2++;
            }
            square_points2++;
             pi = Double.valueOf(6 * circle_points2) / square_points2;
             values2[v2]=pi;
             v2++;
            
        }
        System.out.println();
        System.out.println("Final Estimation of Pi in 3D space is = " + pi);
        final XYSeries series3 = new XYSeries("");
         final XYSeries series4 = new XYSeries("");
        for (int j = 0; j < 1000; j++) {
            double g=values2[j]-Double.valueOf(3.14);
           
            
           series3.add(no_of_points2[j],Math.log(values2[j]));
            series4.add(no_of_points2[j],g);
            
        }
         for (int j = 0; j < 1000; j++) {
           
            System.out.println(no_of_points2[j]);
            
         }
         for (int j = 0; j < 1000; j++) {
           
            System.out.println(values2[j]);
         }
        final XYSeriesCollection data3 = new XYSeriesCollection(series);
        final XYSeriesCollection data4 = new XYSeriesCollection(series2);
        final JFreeChart chart3 = ChartFactory.createXYLineChart(
                "3D log(value) VS No of points",
                
                "No of points",
                "log(value)",
                data3,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final JFreeChart chart4 = ChartFactory.createXYLineChart(
                "3D Error VS No of points",
                "No of points",
                "Error",
                data4,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        JFrame frame2 = new JFrame();
        
        final ChartPanel chartPanel3 = new ChartPanel(chart3);
        final ChartPanel chartPanel4 = new ChartPanel(chart4);
        

        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JPanel jpanel2=new JPanel();
        jpanel2.setBackground(Color.WHITE);
        jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.Y_AXIS));
        jpanel2.add(chartPanel3);
        jpanel2.add(chartPanel4);
        frame2.add(jpanel2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(1200, 500);
        frame2.setVisible(true);
         for (i = 0; i < position.length; i++) {
             position[i]=0;
         }
         for (i = 0; i < 1000000; i++) {
            pos=0;
              for (int j = 0; j< 100; j++) {
              
               rand_num=Math.random();
             
             if(rand_num<0.5)
             { 
                 pos++;
             
             }
                else
             {
                 pos--;
             }
             
              }
             
             position[pos+100]= position[pos+100]+1;
             
             
             
             
                         
         }
           final XYSeries series5 = new XYSeries("");
           for (i = 0; i < 1000; i++) {
             series5.add(i,position[i]);
         }
        
       
       
        final XYSeriesCollection data5 = new XYSeriesCollection(series5);
        final JFreeChart chart5 = ChartFactory.createXYLineChart(
                "Random_Number VS Position",
                "Position",
                "Random Number",
                data5,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
       
        JFrame frame3 = new JFrame();
        
        final ChartPanel chartPanel5 = new ChartPanel(chart5);
      
        

        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JPanel jpanel3=new JPanel();
        jpanel3.setBackground(Color.WHITE);
        jpanel3.setLayout(new BoxLayout(jpanel3, BoxLayout.Y_AXIS));
        jpanel3.add(chartPanel5);
        
        frame3.add(jpanel3);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(1200, 500);
        frame3.setVisible(true);
        
        
       */
        
        
    }

}
