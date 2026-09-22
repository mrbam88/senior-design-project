

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



public class InitializeTaskTask extends Task{

 public InitializeTaskTask(String id){
  super(id,"InitializeTask");
 }



 public void execute() throws TaskException{

        InitConsume  eiInitConsume=(InitConsume)this.getFirstInputOfType("InitConsume");             

        InitFrameFact  eiInitFrameFact=(InitFrameFact)this.getFirstInputOfType("InitFrameFact");             

        CitizenStatus  eiCitizenStatus=(CitizenStatus)this.getFirstInputOfType("CitizenStatus");             
			
        CoordAppApp eaCoordApp=(CoordAppApp)this.getApplication("CoordApp");
			
        LocAppApp eaLocApp=(LocAppApp)this.getApplication("LocApp");

  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
                TaskOutput	outputsdefault=findOutputAlternative("default",
                        	outputs);
  		
		CoordCitConsume outputsdefaultCoordCitConsume=
			(CoordCitConsume)
				outputsdefault.getEntityByType("CoordCitConsume");
		YellowPages yp=null; // only available for initiators of interactions


//#start_node:INGENIASCodeComponent4 <--- DO NOT REMOVE THIS

//@author William R Moser <william.moser@temple.edu>
Random rand = new Random();
double home[] = eaLocApp.randHome();
double work[] = eaLocApp.randWork();
double resistance = (rand.nextDouble()*.7)+.3; //resistance level, can resist up to 20% of illness
eiCitizenStatus.setSickness(0);
eiCitizenStatus.setCitX(home[0]); 
eiCitizenStatus.setCitY(home[1]);
eiCitizenStatus.setAgentResistance(resistance);
eiInitFrameFact.setHomeX(home[0]);
eiInitFrameFact.setHomeY(home[1]);
eiInitFrameFact.setWorkX(work[0]);
eiInitFrameFact.setWorkY(work[1]);
double[][] path = eaLocApp.moveHome2Work(home,work);
eiCitizenStatus.setPath(path);

ingenias.jade.components.AgentWindowAppApp gui = AgentWindowAppInit.getInstance();
int ID = gui.getID();
int[] input = gui.getInputs();
eiCitizenStatus.setPercInfect(input[1]);
eiCitizenStatus.setLengthInfect(input[0]);
if(input[3]==2){
    eaCoordApp.setSick(input[0], resistance);
    eiCitizenStatus.setStatus(2);
}
if(input[3]==1)
    eiCitizenStatus.setStatus(1);

eiCitizenStatus.setAgentID(ID);
gui.placeAgent(ID, home[0], home[1],input[3]);


//#end_node:INGENIASCodeComponent4 <--- DO NOT REMOVE THIS

 }
 
}

 