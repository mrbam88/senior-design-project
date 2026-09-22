
/**
 *
 * @author William R Moser, william.moser@temple.edu
 *
 * LocApp provides several utilities for agents, mostly generating intial values for the agent
 */
package ingenias.jade.components;

import java.util.Random;


public  class LocAppAppImp extends LocAppApp{

 public LocAppAppImp(){
  super();
 }
////////////////////////////////////////
        Random rand = new Random();
        public boolean IsActive = false;
        public int count_dir = 1;
        public int move_index = 0;
        public static double DIST_MAX=100.0;
        public double number_of_moves = 30+(rand.nextDouble()*25);
        public double [] work1 = new double[2];
        public double  [] home1 = new double[2];
        public double LIM_X = 700;
	public double LIM_Y= 600;
        public static double [] current_location = new double[2];

   public int getNumMoves (){
       return((int) number_of_moves);
    }

public double[] setCurrentLocation(double MyX, double MyY){
    current_location[0]=MyX;
    current_location[1]=MyY;
    return(current_location);
}


public boolean proxQuery(double HisX, double HisY){
    boolean is_close = closeEnough(current_location[0], current_location[1], HisX, HisY, DIST_MAX);
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
        public double[] randHome(){
            Random rand = new Random ();

            double[] Home={0,0};
             Home[0]= rand.nextDouble() * LIM_X;
             Home[1]= rand.nextDouble() * LIM_Y;
              return(Home);
        }
        public double[] randWork(){
            Random rand = new Random ();
            double[] Work={0,0};
             Work[0]= rand.nextDouble() * LIM_X;
             Work[1]= rand.nextDouble() * LIM_Y;
             return(Work);
        }
       public double[][] moveHome2Work(double[] Work, double[] Home){
            double[][] intermed= new double[2][(int) number_of_moves];
            double dx=0;
            double dy=0;
            double norm= number_of_moves;
            intermed[0][0]=Home[0];
            intermed[1][0]=Home[1];
            double distX=Math.abs(Work[0]-Home[0]);
	    double distY=Math.abs(Work[1]-Home[1]);
            if(Work[0]<Home[0])
            {
                dx=-1;
            }
            if(Work[0]>Home[0])
            {
                dx=1;
            }
            if(Work[1]<Home[1])
            {
                dy=-1;
            }
            if(Work[1]>Home[1])
            {
                dy=1;
            }

             for(int i = 1; i<=number_of_moves-1; i++)
             {
                  intermed[0][i]=intermed[0][i-1]+(dx*distX/norm);
             }

            for(int j = 1; j<=number_of_moves-1; j++)
             {
                intermed[1][j]=intermed[1][j-1]+(dy*distY/norm);
             }

            return(intermed);


        }

public void showLocation(){
return;
}

}

