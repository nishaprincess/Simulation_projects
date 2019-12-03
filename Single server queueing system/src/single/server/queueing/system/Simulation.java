/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package single.server.queueing.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

;

/**
 *
 * @author acer
 */
public class Simulation extends ApplicationFrame {

    public int count = 0;
    public int p = 0;
    public int k = 0;
    public int kk = 0;
    public String ss;
    public String pp = " ";
    public int event_type = 1;
    public int no_of_customer_delayed = 0;
    public int number_delayed;
    public int q_index = 0;
    public int server = 0;
    public double Qt = 0;
    public double Bt = 0;
    public double mean_interarrival_rate = 1;
    public double mean_service_rate = 0.5;
    public double simulation_clock = 0;
    public double time_of_last_event;
    public double total_delay;
    public int q_size = 100;
    public final double[] time_of_arrival = new double[q_size + 1];
    public final double[] event_list = new double[3];
    public StringBuilder buffer = new StringBuilder();
    public StringBuilder buffer2 = new StringBuilder();
    public StringBuilder buffer3 = new StringBuilder();
    public final double[] inter_arrival = new double[1000000];
    public final double[] arrival = new double[1000000];
    public final double[] dept = new double[1000000];
    public final double[] service_time = new double[1000000];
    public int a = 0;
    public int d = 0;
    public String[] customer_circle = new String[1000000];
    
    public String cc;
    public int customer_circle_size = 0;
    public int  customer_circle_size_start= 0;

    private void output_in_file() {
        try (PrintWriter out = new PrintWriter("report.txt")) {
            out.println(buffer.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        try (PrintWriter out = new PrintWriter("service.txt")) {
            out.println(buffer2.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        try (PrintWriter out = new PrintWriter("arrival.txt")) {
            out.println(buffer3.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void run_simulation() {
        try {
            String input = Files.readAllLines(Paths.get("in.txt")).get(0);
            String[] param = input.split("\\s");
            mean_interarrival_rate = Double.valueOf(param[0]);
            mean_service_rate = Double.valueOf(param[1]);
            number_delayed = Integer.valueOf(param[2]);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        //system_state
        q_index = 0;
        server = 0;
        time_of_last_event = 0.0;

        //event_list
        simulation_clock = 0;
        event_list[1] = simulation_clock + func(mean_interarrival_rate);
        event_list[2] = 1.0e+30;
        //statistical counter
        no_of_customer_delayed = 0;
        total_delay = 0.0;
        Qt = 0.0;
        Bt = 0.0;

        while (no_of_customer_delayed < number_delayed) {

            if (count <= 6) // display/center the jdialog when the button is pressed
            {
                count++;
                JFrame frame = new JFrame();
                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
                JLabel label = new JLabel("count" + count);
                String gg = " ";

                if (event_type == 1 && count > 1) {
                    cc = " ";
                    ss = " ";
                    gg = "Arrival time=" + String.format("%.2f", simulation_clock);
                    customer_circle[customer_circle_size] = String.format("%.2f", simulation_clock);
                    customer_circle_size++;
                    for (int gh = customer_circle_size_start; gh < customer_circle_size; gh++) {
                        cc = cc + customer_circle[gh]+" ";
                    }
                     for (int gh = customer_circle_size_start+1; gh < customer_circle_size; gh++) {
                        ss = ss + customer_circle[gh]+" ";
                    }
                } else if (event_type == 2) {
                    cc = " ";
                    ss = " ";
                    gg = "Departure time=" + String.format("%.2f", simulation_clock);
                    customer_circle_size_start++;
                    for (int gh = customer_circle_size_start; gh < customer_circle_size; gh++) {
                        cc = cc + customer_circle[gh]+" ";
                    }
                    for (int gh = customer_circle_size_start+1; gh < customer_circle_size; gh++) {
                        ss = ss + customer_circle[gh]+" ";
                    }
                } else if (count == 1) {
                    gg = "Intialization time=" + String.format("%.2f", simulation_clock);
                }
                JLabel label1 = new JLabel("server status" + server);
                JLabel label2 = new JLabel("no in the queue" + q_index);

                

                
                JLabel label4 = new JLabel("time of last event " + String.format("%.2f", time_of_last_event));

                JPanel south = new JPanel();
                south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
                JPanel south1 = new JPanel();
                JPanel south2 = new JPanel();
                south.add(south1);
                south.add(south2);
                south1.add(label);
                south1.add(new JLabel(gg));
                south2.add(new JLabel(cc));
                //south.setSize(100, 500);
                south.setBackground(Color.PINK);
                JPanel cen = new JPanel();
                JPanel cen1 = new JPanel();
                cen1.add(label1);
                JPanel cen2 = new JPanel();
                cen2.add(label2);
                JPanel cen3 = new JPanel();
                JPanel cen31 = new JPanel();
                JPanel cen32 = new JPanel();
                cen3.setLayout(new BoxLayout(cen3, BoxLayout.Y_AXIS));
                cen31.add(new JLabel("time of arrival"));
                cen32.add(new JLabel(ss));
                cen3.add(cen31);
                cen3.add(cen32);
                JPanel cen4 = new JPanel();
                cen4.add(label4);
                

                cen4.setBackground(Color.WHITE);
                cen1.setBackground(Color.blue);
                cen2.setBackground(Color.CYAN);
                cen3.setBackground(Color.BLUE);
                cen.setLayout(new BoxLayout(cen, BoxLayout.X_AXIS));

                cen.add(cen1);
                cen.add(cen2);
                cen.add(cen3);
                cen.add(cen4);

                JPanel north1 = new JPanel();
                JPanel north11 = new JPanel();
                JPanel north12 = new JPanel();
                JLabel label11 = new JLabel("Simulation clock:");
                JTextField text11 = new JTextField(String.format("%.2f", simulation_clock));
                JLabel label12a = new JLabel("next arrival=" + String.format("%.2f", event_list[1]));
                JLabel label12d = new JLabel("\ndeparture=" + String.format("%.2f", event_list[2]));
                JLabel label21 = new JLabel("No of delays required=" + no_of_customer_delayed);
                JLabel label22 = new JLabel("Total_delay=" + String.format("%.2f", total_delay));
                JLabel label23 = new JLabel("B(t)=" + String.format("%.2f", Bt));
                JLabel label24 = new JLabel("Q(t)=" + String.format("%.2f", Qt));

                north11.setBackground(Color.PINK);
                north12.setBackground(Color.WHITE);
                north11.add(label11);
                north11.add(text11);

                north12.add(label12a);
                north12.add(new JLabel("\n"));
                north12.add(label12d);
                north1.add(north11);
                north1.add(north12);
                north1.setLayout(new BoxLayout(north1, BoxLayout.X_AXIS));
                north1.setBackground(Color.PINK);
                JPanel north21 = new JPanel();
                north21.setBackground(Color.BLUE);
                JPanel north22 = new JPanel();
                north22.setBackground(Color.white);
                JPanel north23 = new JPanel();
                north23.setBackground(Color.CYAN);
                JPanel north24 = new JPanel();
                north24.setBackground(Color.PINK);
                JPanel north2 = new JPanel();
                north21.add(label21);
                north22.add(label22);
                north23.add(label23);
                north24.add(label24);
                north2.add(north21);
                north2.add(north22);
                north2.add(north23);
                north2.add(north24);
                north2.setLayout(new BoxLayout(north2, BoxLayout.X_AXIS));

                north2.setBackground(Color.GRAY);

                JPanel north = new JPanel();
                north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
                north.add(north1);
                north.add(north2);

                container.add(south);
                container.add(cen);
                container.add(north);
                frame.add(container);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 500);
                frame.setVisible(true);
                ss = null;
                // p++;

            }

            //clock settings
            double minimum_time = 1.0e+29;
            for (int i = 1; i <= 2; ++i) {
                if (event_list[i] < minimum_time) {
                    minimum_time = event_list[i];
                    event_type = i;
                }
            }
            if (event_type == 0) {
                buffer.append(String.format("Total Simulation time %f", simulation_clock));
                output_in_file();
                System.exit(1);
            }
            simulation_clock = minimum_time;
            //update counters
            double width;
            p++;
            System.out.println("count" + p);
            System.out.println("time of last event:" + time_of_last_event);
            System.out.println("total delay:" + total_delay);
            width = simulation_clock - time_of_last_event;
            time_of_last_event = simulation_clock;

            System.out.println("sim time" + simulation_clock);

            Qt += q_index * width;
            System.out.println("Q(t)" + Qt);
            Bt += server * width;
            System.out.println("B(t)" + Bt);

            if (event_type == 1) {
               
                double delay;
                double ll = func(mean_interarrival_rate);
                event_list[1] = simulation_clock + ll;
                arrival[a] = event_list[1];
                a++;
                inter_arrival[k] = ll;
                buffer3.append(String.format("\n customer no %d  and interarrival :%5.3f minutes\n\n", no_of_customer_delayed + 1, inter_arrival[k]));
                k++;
                System.out.println(" next arrival time:" + event_list[1]);

                if (server == 1) {
                    q_index++;
                    if (q_index > q_size) {

                        buffer.append("\nOverflow of the array time_arrival at");
                        buffer.append(String.format(" time %f", simulation_clock));
                    }
                    time_of_arrival[q_index] = simulation_clock;
                } else {

                    delay = 0.0;
                    total_delay = total_delay + delay;
                    ++no_of_customer_delayed;
                    server = 1;
                    double o = func(mean_service_rate);
                    event_list[2] = simulation_clock + o;
                    service_time[kk] = o;
                    buffer2.append(String.format("\n customer no %d  and service time :%5.3f minutes\n\n", no_of_customer_delayed, service_time[kk]));
                    kk++;
                    dept[d] = event_list[2];
                    d++;
                    System.out.println("dept time:" + event_list[2]);
                }
            } else if (event_type == 2) {
                
                double delay2;
                if (q_index == 0) {
                    server = 0;
                    event_list[2] = 1.0e+30;
                } else {
                    --q_index;
                    delay2 = simulation_clock - time_of_arrival[1];
                    total_delay = total_delay + delay2;
                    ++no_of_customer_delayed;
                    double o = func(mean_service_rate);
                    event_list[2] = simulation_clock + o;
                    dept[d] = event_list[2];
                    d++;
                    service_time[kk] = o;
                    buffer2.append(String.format("\n customer no %d  and service time :%5.3f minutes\n\n", no_of_customer_delayed, service_time[kk]));
                    kk++;

                    for (int i = 1; i <= q_index; ++i) {
                        time_of_arrival[i] = time_of_arrival[i + 1];

                    }
                }
            }

        }
        final XYSeries series = new XYSeries("");
        final XYSeries series2 = new XYSeries("");
        for (int i = 0; i < k; i++) {
            // System.out.println("a"+i+inter_arrival[i]);
            series.add(inter_arrival[i], mean_interarrival_rate);

        }
        for (int i = 0; i < kk; i++) {
            // System.out.println("a"+i+inter_arrival[i]);
            series2.add(service_time[i], mean_service_rate);

        }

        final XYSeriesCollection data = new XYSeriesCollection(series);
        final XYSeriesCollection data2 = new XYSeriesCollection(series2);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Mean Interval VS Inter Arrival ",
                
                "Inter Arrival",
                "Mean Interval",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        final JFreeChart chart2 = ChartFactory.createXYLineChart(
                "Mean Service VS Service Time ",
                "Service Time",
                "Mean Service",
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

        buffer.append(String.format("\nd(n)%11.3f minutes\n\n", total_delay / no_of_customer_delayed));
        buffer.append(String.format("\nq(n)%10.3f\n\n", Qt / simulation_clock));
        buffer.append(String.format("u(n)%15.3f\n\n", Bt / simulation_clock));
        buffer.append(String.format(" Time simulation ended%12.3f minutes", simulation_clock));
        output_in_file();
    }

    public double func(double mean) {
        return -mean * Math.log(Math.random());
    }

    public Simulation(String title) {
        super(title);

    }

}
