

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



public class CommuteTaskTask extends Task{

 public CommuteTaskTask(String id){
  super(id,"CommuteTask");
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

        CoordCitConsume  eiCoordCitConsume=(CoordCitConsume)this.getFirstInputOfType("CoordCitConsume");             

        CitizenStatus  eiCitizenStatus=(CitizenStatus)this.getFirstInputOfType("CitizenStatus");             
			
        CoordAppApp eaCoordApp=(CoordAppApp)this.getApplication("CoordApp");
			
        LocAppApp eaLocApp=(LocAppApp)this.getApplication("LocApp");

        Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		TaskOutput	outputsdefault=findOutputAlternative("default",
 				outputs);
        YellowPages yp=null; // only available for initiators of interactions

//#start_node:INGENIASCodeComponent5 <--- DO NOT REMOVE THIS

// @author William R Moser <william.moser@temple.edu>
//System.out.println("Commute Task started");
int number_of_moves = eaLocApp.getNumMoves();
double[][] path=eiCitizenStatus.getPath();
int ID = eiCitizenStatus.getAgentID();
eaCoordApp.agentMove(ID, path, number_of_moves);
//System.out.println("commute Task Finished");

//#end_node:INGENIASCodeComponent5 <--- DO NOT REMOVE THIS

 }
 
}

 