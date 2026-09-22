//@author William R Moser, william.moser@temple.edu
//Sickness contains a counter to reduce the amount of time the agent is sick for every time step

package ingenias.jade.components;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sickness {
    public int Status = 1;
    public int time_sick = 0;
    public void getSick(int Sickness, double Resistance){
   
                final int Sickness1 = Sickness;
                final double Resistance1 = Resistance;
                new Thread(){
			public void run(){
                            try {

                                countDown(Sickness1, Resistance1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Sickness.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}.start();
    }
      public void countDown(int Sickness, double Resistance) throws InterruptedException {
            time_sick = (int)(Sickness*Resistance);
            while(time_sick>=0){
            if(time_sick>=1){
                time_sick=time_sick-1;
                Status = 2; //susceptible
                Thread.sleep(13600);
            }
            if(time_sick == 0 && Status == 2){//if the sick timer runs out, go to removed
                Status = 3; //removed
                Thread.yield();
            }

      }//end while(time_sick)
    }
}
