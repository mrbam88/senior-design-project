

/*
    @Author William R Moser
 *
 * This in the main function, launching a JADE window and one (or more) coordinators.
 
   Code was mdified based on IAF file by Jorge Gomez Sanz, 2005

    The file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 


*/


package ingenias.jade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import jade.core.*;
import ingenias.jade.mental.*;

import ingenias.jade.graphics.MainInteractionManager;

public class RunSimulation {

  public static void main(String args[]) throws Exception{
		IAFProperties.setGraphicsOn(false);

		new Thread(){
			public void run(){
				String[] args1=new String[5];
                                args1[0]="-gui";
				args1[1]="-port";
				args1[2]="60000";
				args1[3]="-file-dir";
				args1[4]="jade/";
				jade.Boot.main(args1);		
			}
		}.start();

        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");     
        
        // Waits for JADE to start
        boolean notConnected=true;
		
		while (notConnected){			
				try {
					Socket s=new Socket("localhost",Integer.parseInt("60000"));
					notConnected=false;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					
					System.err.println("Error: "+e.getMessage());
					System.err.println("Reconnecting in one second");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
		}

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);
{
           // Create a new agent
        final jade.wrapper.AgentController agcCoordinator_0CoordDeploy = ac.createNewAgent("Coordinator_0CoordDeploy",
            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
        new Thread(){
          public void run(){
            try {
              agcCoordinator_0CoordDeploy.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();
        Thread.sleep(2000);
//        // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_1CoordDeploy = ac.createNewAgent("Coordinator_1CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_1CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();
//        // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_2CoordDeploy = ac.createNewAgent("Coordinator_2CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_2CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();
// // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_3CoordDeploy = ac.createNewAgent("Coordinator_3CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_3CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();
//         // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_4CoordDeploy = ac.createNewAgent("Coordinator_4CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_4CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();
//         // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_5CoordDeploy = ac.createNewAgent("Coordinator_5CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_5CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();
//         // Create a new agent
//        final jade.wrapper.AgentController agcCoordinator_6CoordDeploy = ac.createNewAgent("Coordinator_6CoordDeploy",
//            "ingenias.jade.agents.CoordinatorJADEAgent", new Object[0]);
//
//
//        new Thread(){
//          public void run(){
//            try {
//              agcCoordinator_6CoordDeploy.start();
//            } catch (Exception e){
//              e.printStackTrace();
//            }
//          }
//        }.start();

}
//	      MainInteractionManager.getInstance().setTitle("node ThreeAgentsDeployment");
     }
}

 