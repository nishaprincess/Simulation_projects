/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.system;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 *
 * @author acer
 */
public class InventorySystem {

    public static int amount, bigs, initial_inv_level, inv_level, next_event_type, num_events, num_months, num_values_demand, smalls;
    public static double area_holding, area_shortage, holding_cost, incremental_cost, maxlag, mean_interdemand, minlag;
    public static double[] prob_distrib_demand = new double[26];
    public static double setup_cost, shortage_cost, sim_time, time_last_event, total_ordering_cost;
    public static double[] time_next_event = new double[5];
    public static StringBuilder buffer = new StringBuilder();

    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int i, num_policies;
        num_events = 4;
        Scanner in = new Scanner(System.in);
        initial_inv_level = in.nextInt();
        num_months = in.nextInt();
        num_policies = in.nextInt();
        num_values_demand = in.nextInt();
        mean_interdemand = in.nextDouble();
        setup_cost = in.nextDouble();
        incremental_cost = in.nextDouble();
        holding_cost = in.nextDouble();
        shortage_cost = in.nextDouble();
        minlag = in.nextDouble();
        maxlag = in.nextDouble();

        for (int j = 1; j <= num_values_demand; ++j) {
            prob_distrib_demand[j] = in.nextDouble();
        }
        buffer.append(String.format("Single-product Inventory System\n\n"));
        buffer.append(String.format("Initial inventory level%24d items \n\n", initial_inv_level));
        buffer.append(String.format("Number of demand sizes%25d\n\n", num_values_demand));
        buffer.append(String.format("Distribution function of demand sizes  "));
        for (int j = 1; j <= num_values_demand; ++j) {
            buffer.append(String.format("%8.3f", prob_distrib_demand[j]));
        }
        buffer.append(String.format("\n\nMean interdemand time%26.2f\n\n", mean_interdemand));
        buffer.append(String.format("Delivery lag range%29.2f to%10.2f months\n\n", minlag, maxlag));
        buffer.append(String.format("Length of Simulation%23d months\n\n", num_months));
        buffer.append(String.format("K=%6.1f  i=%6.1f  h=%6.1f  pi=%6.1f\n\n", setup_cost, incremental_cost, holding_cost, shortage_cost));
        buffer.append(String.format("Number of policies%29d\n\n", num_policies));
        buffer.append(String.format("                 Average       Average"));
        buffer.append(String.format("        Average        Average\n"));
        buffer.append(String.format("  Policy       total cost    ordering cost"));
        buffer.append(String.format("  holding cost   shortage cost"));

        for (int j = 1; j <= num_policies; ++j) {
            smalls = in.nextInt();
            bigs = in.nextInt();

            //initialize
            sim_time = 0.0;

            inv_level = initial_inv_level;
            time_last_event = 0.0;

            total_ordering_cost = 0.0;
            area_holding = 0.0;
            area_shortage = 0.0;

            time_next_event[1] = 1.0e+30;
            time_next_event[2] = sim_time + expon(mean_interdemand);
            time_next_event[3] = num_months;
            time_next_event[4] = 0.0;

            //initialize
            do {
                timing();
                update_time_avg_stats();

                switch (next_event_type) {
                    case 1:
                        order_arrival();
                        break;
                    case 2:
                        demand();
                        break;
                    case 4:
                        evaluate();
                        break;
                    case 3:
                        report();
                        break;
                }
            } while (next_event_type != 3);

        }
        write_output();

    }

    public static void write_output() throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("out.txt")) {
            out.println(buffer.toString());
        }

    }

    public static double expon(double mean) {
        return -mean * Math.log(Math.random());
    }

    public static void timing() throws FileNotFoundException {
        int i;
        double min_time_next_event = 1.0e+29;
        next_event_type = 0;
        for (i = 1; i <= num_events; ++i) {
            if (time_next_event[i] < min_time_next_event) {
                min_time_next_event = time_next_event[i];
                next_event_type = i;
            }
        }
        if (next_event_type == 0) {

            buffer.append(String.format("\nEvent list is empty at time %f", sim_time));
            write_output();
            System.exit(1);
        }
        sim_time = min_time_next_event;
    }

    public static void update_time_avg_stats() {
        double time_since_last_event;
        time_since_last_event = sim_time - time_last_event;
        time_last_event = sim_time;

        if (inv_level < 0) {
            area_shortage -= inv_level * time_since_last_event;
        } else if (inv_level > 0) {
            area_holding += inv_level * time_since_last_event;
        }
    }

    public static void order_arrival() {
        inv_level += amount;
        time_next_event[1] = 1.0e+30;
    }

    public static void demand() {
        inv_level -= random_integer();
        time_next_event[2] = sim_time + expon(mean_interdemand);
    }

    public static void evaluate() {
        if (inv_level < smalls) {
            amount = bigs - inv_level;
            total_ordering_cost += setup_cost + incremental_cost * amount;
            time_next_event[1] = sim_time + uniform(minlag, maxlag);
        }
        time_next_event[4] = sim_time + 1.0;
    }

    public static int random_integer() {
        int i;
        double u = Math.random();
        for (i = 1; u >= prob_distrib_demand[i]; ++i) ;
        return i;

    }

    public static double uniform(double a, double b) {
        return a + Math.random() * (b - a);
    }

    public static void report() {
        double avg_holding_cost, avg_ordering_cost, avg_shortage_cost;

        avg_ordering_cost = total_ordering_cost / num_months;
        avg_holding_cost = holding_cost * area_holding / num_months;
        avg_shortage_cost = shortage_cost * area_shortage / num_months;

        buffer.append(String.format("\n\n(%3d,%3d)%15.2f%15.2f%15.2f%15.2f", smalls, bigs,
                avg_ordering_cost + avg_holding_cost + avg_shortage_cost,
                avg_ordering_cost, avg_holding_cost, avg_shortage_cost));
    }
}
