

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package ingenias.jade.components;
import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.comm.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;

public class WaitForSickTask extends Task{

 public WaitForSickTask(String id){
  super(id,"WaitForSick");
 }

 public void execute() throws TaskException{
   try{
     execute2();
 }
   catch(InterruptedException e){
       System.out.println(e);
   }
 }
 public void execute2() throws InterruptedException{

        DoWaitForSick  eiDoWaitForSick=(DoWaitForSick)this.getFirstInputOfType("DoWaitForSick");

        CitizenStatus  eiCitizenStatus=(CitizenStatus)this.getFirstInputOfType("CitizenStatus");             
			
        CoordAppApp eaCoordApp=(CoordAppApp)this.getApplication("CoordApp");

  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
    		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  				outputs);
  		
		RuntimeConversation outputsdefaultCoordCitInteraction=
			(RuntimeConversation)
				outputsdefault.getEntityByType("CoordCitInteraction");
			
		IsSickFlag outputsdefaultIsSickFlag=
			(IsSickFlag)
				outputsdefault.getEntityByType("IsSickFlag");
			
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type CoordCitInteraction by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");
        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "CoordRole"
      	//eoCoordCitInteraction.addCollaborators("Local ID of the collaborator");
       	
//#start_node:INGENIASCodeComponent1 <--- DO NOT REMOVE THIS	

//@author William R Moser, william.moser@temple.edu
//System.out.println("Wait Task Started");
double[] status = new double[3]; 
double SX = 0;
double SY = 0;
int ID = eiCitizenStatus.getAgentID();
do{
    status = eaCoordApp.getAInfo();
    SX = status[0];
    SY = status[1];
    ID = eiCitizenStatus.getAgentID();
}while(status[2]==1 || SX == 0 || SY == 0);
if(status[2]==2){
    outputsdefaultIsSickFlag.setSickX(SX);
    outputsdefaultIsSickFlag.setSickY(SY);
    outputsdefaultIsSickFlag.setAgentID(ID);
    //System.out.println("Wait For sick output is "+SX +" " +SY +" " +ID +" " +status[2]);
}
//System.out.println("Wait Task Finished");
//#end_node:INGENIASCodeComponent1 <--- DO NOT REMOVE THIS

 }
 
}

 