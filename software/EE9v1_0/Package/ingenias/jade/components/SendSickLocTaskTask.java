

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

public class SendSickLocTaskTask extends Task{

 public SendSickLocTaskTask(String id){
  super(id,"SendSickLocTask");
 }

 public void execute() throws TaskException{

        SickLocFF  eiSickLocFF=(SickLocFF)this.getFirstInputOfType("SickLocFF");             

        // This means that the task participates in the interaction SendInfect
        RuntimeConversation  conversationContextSendInfect=this.getConversationContext();

  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  			outputs);
  		
		SendSickLocFF outputsdefaultSendSickLocFF=
			(SendSickLocFF)
				outputsdefault.getEntityByType("SendSickLocFF");
		
        YellowPages yp=null; // only available for initiators of interactions

//#start_node:INGENIASCodeComponent6 <--- DO NOT REMOVE THIS	

//@author William R Moser, william.moser@temple.edu
//System.out.println("SendLocTask Start");
double SX = eiSickLocFF.getSickX();
double SY = eiSickLocFF.getSickY();
int ID = eiSickLocFF.getAgentID();
double PercInfect = eiSickLocFF.getPercInfect();
double LengthInfect = eiSickLocFF.getLengthInfect();
outputsdefaultSendSickLocFF.setSickX(SX);
outputsdefaultSendSickLocFF.setSickY(SY); 
outputsdefaultSendSickLocFF.setAgentID(ID);
outputsdefaultSendSickLocFF.setPercInfect((int)PercInfect);
outputsdefaultSendSickLocFF.setLengthInfect(LengthInfect);
//System.out.println("Send Task Finished");

//#end_node:INGENIASCodeComponent6 <--- DO NOT REMOVE THIS

 }
 
}

 