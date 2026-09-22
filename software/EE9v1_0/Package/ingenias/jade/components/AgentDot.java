
/**
 *
 * @author William R Moser
 *
 * Agent Dot draws the colored dots for the application
 */


package ingenias.jade.components;

import java.awt.Color;
import java.awt.Graphics;

public class AgentDot {
      private final int BALLDIAMETER = 20;
      public double x;
      public double y;
      public int state;
      public int agentnumber;
      public boolean initiated=false;

      public void drawDots( Graphics g ) {  //pass graphics
           g.setColor( Color.BLACK ); //outside border
           g.drawOval((int) x, (int) y, BALLDIAMETER, BALLDIAMETER ); //draw at the postion
           if(state==1){
           g.setColor( Color.YELLOW );
           }
           else if(state==2){
           g.setColor( Color.RED );
           }
           else if(state==3){
           g.setColor( Color.GREEN );
           }
           g.fillOval( (int) x+1, (int) y+1, BALLDIAMETER-2, BALLDIAMETER-2 );

      }
}
