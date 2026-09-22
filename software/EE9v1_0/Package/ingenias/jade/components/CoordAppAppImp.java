/**
 *
 * @author William R Moser, william.moser@temple.edu
 *
 * CoordApp provides several utilities for agents, mostly movement and contracting illnesses
 */

package ingenias.jade.components;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import ingenias.jade.exception.*;
import ingenias.jade.mental.*;




public  class CoordAppAppImp extends CoordAppApp{

 public CoordAppAppImp(){
  super();
 }
Sickness sick = new Sickness();
double[] location = new double[3];
public static double DIST_MAX=30.0;

public double[] getAInfo(){
     double [] CitInf = new double[3];
     CitInf[0]=location[0];
     CitInf[1]=location[1];
     CitInf[2]=sick.Status;
     return(CitInf);
 }

public boolean proxQuery(double HisX, double HisY){
    boolean is_close = closeEnough(location[0], location[1], HisX, HisY, DIST_MAX);
    //  System.out.println("Agent prox check is "+is_close);
    //  System.out.println("because this agent is at "+location[0] +"," +location[1] +" and the other is at " +HisX +"," +HisY);
    return(is_close);
}
public static boolean closeEnough(double x1, double y1, double x2, double y2,double distMax){
		double distX=Math.abs(x2-x1);
		double distY=Math.abs(y2-y1);
                if(x1==x2 || y1==y2)
                {
                double dist=(distX+distY);

                return (dist<=distMax);

                } else {
		double dist=Math.sqrt(distX*distX + distY*distY);

                return (dist<=distMax);
                }

	}
public void setSick(int Sickness, double Resistance){
     sick.getSick(Sickness, Resistance);
 }

 public void agentMove(int AgentID, double[][] path, int number_of_moves){
                final int AgentID1 = AgentID;
                final double[][] path1 = path;
                final int number_of_moves1 = number_of_moves;
                if(path==null || AgentID == 0){
                     long t0, t1;
                     t0 =  System.currentTimeMillis();
                         do{
                              t1 = System.currentTimeMillis();
                              System.out.println("Time Sink. . .");
                        } while ((t1 - t0) < (500));
                 }
                 else if(path!=null && AgentID != 0){
                   new Thread(){
                       public void run(){
                            try {
                                movIng(AgentID1,path1,number_of_moves1);
                            } catch (InterruptedException ex) {
                               System.out.println("exception reported in agentMove!");
                            }
			}
                    }.start();
                 }
    }
public void movIng(int AgentID, double[][] path, int number_of_moves)throws InterruptedException{
    ingenias.jade.components.AgentWindowAppApp gui = AgentWindowAppInit.getInstance();
    int move_index = 0;
     int stopcount = 0;
    int count_dir = 1;
boolean IsActive=true;

    while(IsActive && path!=null && number_of_moves!=0){
            Thread.sleep(4000);
            if(move_index != (number_of_moves-1) && move_index != 0)
            {
                move_index=move_index+count_dir;
                location[0] = path[0][move_index];
                location[1] = path[1][move_index];
                gui.placeAgent(AgentID, path[0][move_index], path[1][move_index], sick.Status);
                    stopcount++;
            }
            else if(move_index == 0)
            {
                count_dir=1;
                move_index=move_index+count_dir;
                location[0] = path[0][move_index];
                location[1] = path[1][move_index];
                gui.placeAgent(AgentID, path[0][move_index], path[1][move_index], sick.Status);
            }
            else if(move_index == (number_of_moves-1))
            {
                count_dir=-1;
                move_index=move_index+count_dir;
                location[0] = path[0][move_index];
                location[1] = path[1][move_index];
                gui.placeAgent(AgentID, path[0][move_index], path[1][move_index], sick.Status);
            }
            if(sick.Status==2){
            DoWaitForSick nevent= new DoWaitForSick();
            try {
                    getOwner().getMSM().addMentalEntity(nevent);
            } catch (ingenias.exception.InvalidEntity ex){
                    ex.printStackTrace();
            }
            }
            //System.out.println(AgentID +" " + (int)path[0][move_index] +" " + (int)path[1][move_index]);

        }
    }
}
      






 