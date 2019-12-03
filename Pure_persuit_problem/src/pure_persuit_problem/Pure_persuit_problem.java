/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_persuit_problem;

import java.util.Scanner;

public class Pure_persuit_problem {
public static double dis;
public static double init_f_x,init_f_y;
public static int time;
public static double[] b_x = new double[100];
public static double[] b_y = new double[100];
public static double[] f_x = new double[100];
public static double[] f_y = new double[100];
public static double VF;

   
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //initial fighter position 
        init_f_x= in.nextDouble();
        init_f_y= in.nextDouble();      
        f_x[0]=init_f_x ;
        f_y[0]= init_f_y;
        VF = in.nextDouble();
        for (int i = 0; i <= 12; i++) {
            b_x[i] = in.nextDouble();
            b_y[i] = in.nextDouble();
        }
        for (time = 0; time < 12; time++) {
             dis=Math.sqrt(Math.pow((b_y[time] - f_y[time]), 2) + Math.pow((b_x[time] - f_x[time]), 2));
            if (dis < 10) {
               System.out.println("Caught at"+time+"minutes and "+dis+"kms");
                break;
            }
            else
            {
                 double s=(b_y[time]-f_y[time])/dis;
                 double c=(b_x[time]-f_x[time])/dis;
                 f_x[time+1]=f_x[time]+(VF*c);
                 f_y[time+1]=f_y[time]+(VF*s);
            }
                    
        }
        if (time > 11) {
            System.out.println("Target Escaped");
        }
        
        //y increase ,x fixed
        System.out.print("For y increase ,x fixed : ");
        int flag=0;
        f_x[0]=init_f_x;
        f_y[0]=init_f_y;
        while (flag != 1) {
              f_y[0]=f_y[0]+1;
                 for (time = 0; time < 12; time++) {
             dis=Math.sqrt(Math.pow((b_y[time] - f_y[time]), 2) + Math.pow((b_x[time] - f_x[time]), 2));
            if (dis < 10) {
              // System.out.println("Caught at"+time+"minutes and "+dis+"kms");
                break;
            }
            else
            {
                 double s=(b_y[time]-f_y[time])/dis;
                 double c=(b_x[time]-f_x[time])/dis;
                 f_x[time+1]=f_x[time]+(VF*c);
                 f_y[time+1]=f_y[time]+(VF*s);
            }
                    
        }
        if (time > 11) {
            flag=1;
            System.out.println("Target Escaped at ("+f_x[0]+","+f_y[0]+") position");
        }

        }
        //y decrease ,x fixed
        System.out.print("For y decrease ,x fixed : ");
        flag=0;
        f_x[0]=init_f_x;
        f_y[0]=init_f_y;
        while (flag != 1) {
              f_y[0]=f_y[0]-1;
                 for (time = 0; time < 12; time++) {
             dis=Math.sqrt(Math.pow((b_y[time] - f_y[time]), 2) + Math.pow((b_x[time] - f_x[time]), 2));
            if (dis < 10) {
              // System.out.println("Caught at"+time+"minutes and "+dis+"kms");
                break;
            }
            else
            {
                 double s=(b_y[time]-f_y[time])/dis;
                 double c=(b_x[time]-f_x[time])/dis;
                 f_x[time+1]=f_x[time]+(VF*c);
                 f_y[time+1]=f_y[time]+(VF*s);
            }
                    
        }
        if (time > 11) {
            flag=1;
            System.out.println("Target Escaped at ("+f_x[0]+","+f_y[0]+") position");
        }

        }
          //y fixed ,x increase
        System.out.print("For y fixed ,x increase : ");
        flag=0;
        f_x[0]=init_f_x;
        f_y[0]=init_f_y;
        while (flag != 1) {
              f_x[0]=f_x[0]+1;
                 for (time = 0; time < 12; time++) {
             dis=Math.sqrt(Math.pow((b_y[time] - f_y[time]), 2) + Math.pow((b_x[time] - f_x[time]), 2));
            if (dis < 10) {
              // System.out.println("Caught at"+time+"minutes and "+dis+"kms");
                break;
            }
            else
            {
                 double s=(b_y[time]-f_y[time])/dis;
                 double c=(b_x[time]-f_x[time])/dis;
                 f_x[time+1]=f_x[time]+(VF*c);
                 f_y[time+1]=f_y[time]+(VF*s);
            }
                    
        }
        if (time > 11) {
            flag=1;
            System.out.println("Target Escaped at ("+f_x[0]+","+f_y[0]+") position");
        }

        }
          //y fixed ,x decrease
         System.out.print("For y fixed ,x decrease : ");
        flag=0;
        f_x[0]=init_f_x;
        f_y[0]=init_f_y;
        while (flag != 1) {
              f_x[0]=f_x[0]-1;
                 for (time = 0; time < 12; time++) {
             dis=Math.sqrt(Math.pow((b_y[time] - f_y[time]), 2) + Math.pow((b_x[time] - f_x[time]), 2));
            if (dis < 10) {
              // System.out.println("Caught at"+time+"minutes and "+dis+"kms");
                break;
            }
            else
            {
                 double s=(b_y[time]-f_y[time])/dis;
                 double c=(b_x[time]-f_x[time])/dis;
                 f_x[time+1]=f_x[time]+(VF*c);
                 f_y[time+1]=f_y[time]+(VF*s);
            }
                    
        }
        if (time > 11) {
            flag=1;
            System.out.println("Target Escaped at ("+f_x[0]+","+f_y[0]+") position");
        }

        }
        
        

    }
    
}
