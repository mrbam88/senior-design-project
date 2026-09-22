

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

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.JADEAgent;

public  class LocAppInit {
 


 private static Vector<LocAppAppImp> appsinitialised=new Vector<LocAppAppImp>();
 


 public static void initialize(LocAppAppImp app){
  
 }

 public static void shutdown(LocAppAppImp app){
  
 }

public static void shutdown(){


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

}



 public static Vector<LocAppAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static LocAppApp createInstance(){
	LocAppAppImp app=new LocAppAppImp();
    initialize(app);
	appsinitialised.add(app);
   return app;
  }
  public static LocAppApp createInstance(JADEAgent owner){
	LocAppAppImp app=new LocAppAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
   return app;
  }


}

 