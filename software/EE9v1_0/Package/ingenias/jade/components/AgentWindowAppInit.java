

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import java.io.FileNotFoundException;
import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.JADEAgent;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class AgentWindowAppInit {

 private static AgentWindowAppAppImp instance = null;
 

 


 public static void initialize(AgentWindowAppAppImp app){
     		final AgentWindowAppAppImp appF=app;
		new Thread(){
			public void run(){
				appF.showAgentWindow();
			}
		}.start();
 }

 public static void shutdown(AgentWindowAppAppImp app){
  
 }

public static void shutdown(){

   if (instance!=null){
	shutdown(instance);
   }


}




  public static AgentWindowAppApp getInstance(){
   if (instance==null){
            try {
                instance = new AgentWindowAppAppImp();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AgentWindowAppInit.class.getName()).log(Level.SEVERE, null, ex);
            }
    initialize(instance);
   }
   return instance;
  }
    public static AgentWindowAppApp getInstance(JADEAgent owner){
   if (instance==null){
            try {
                instance = new AgentWindowAppAppImp();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AgentWindowAppInit.class.getName()).log(Level.SEVERE, null, ex);
            }
	instance.registerOwner(owner);
    initialize(instance);
   }
   if (instance.getOwner()==null)
	 instance.registerOwner(owner);
	 
   return instance;
  }

}

 