

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

public  class CoordAppInit {
 


 private static Vector<CoordAppAppImp> appsinitialised=new Vector<CoordAppAppImp>();
 


 public static void initialize(CoordAppAppImp app){
  
 }

 public static void shutdown(CoordAppAppImp app){
  
 }

public static void shutdown(){


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

}



 public static Vector<CoordAppAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static CoordAppApp createInstance(){
	CoordAppAppImp app=new CoordAppAppImp();
    initialize(app);
	appsinitialised.add(app);
   return app;
  }
  public static CoordAppApp createInstance(JADEAgent owner){
	CoordAppAppImp app=new CoordAppAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
   return app;
  }


}

 