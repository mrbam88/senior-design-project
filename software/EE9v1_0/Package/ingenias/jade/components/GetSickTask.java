

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



public class GetSickTask extends Task{

 public GetSickTask(String id){
  super(id,"GetSick");
 }

 public void execute() throws TaskException{

        SendSickLocFF  eiSendSickLocFF=(SendSickLocFF)this.getFirstInputOfType("SendSickLocFF");             

        CitizenStatus  eiCitizenStatus=(CitizenStatus)this.getFirstInputOfType("CitizenStatus");             
			
        CoordAppApp eaCoordApp=(CoordAppApp)this.getApplication("CoordApp");

        // This means that the task participates in the interaction SendInfect
        RuntimeConversation  conversationContextSendInfect=this.getConversationContext();


  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  			outputs);
          YellowPages yp=null; // only available for initiators of interactions

//#start_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS	

// @author William R Moser <william.moser@temple.edu>
//System.out.println("SickTask Start");
Random rand = new Random();
double[] status = new double[3];
double SX = 0;
double SY = 0;
status = eaCoordApp.getAInfo();
SX = eiSendSickLocFF.getSickX();
SY = eiSendSickLocFF.getSickY();
int ID = eiSendSickLocFF.getAgentID();
int ThisID = eiCitizenStatus.getAgentID();
double ThisResistance = eiCitizenStatus.getAgentResistance();
//System.out.println("This ID and ID are " +ThisID +" and " +ID);
//System.out.println("This Resist is " +ThisResistance);
double InfectChance = (eiSendSickLocFF.getPercInfect()*0.01);
if (InfectChance > 1)
    InfectChance = 1;
double LengthInfect = eiSendSickLocFF.getLengthInfect();
double RollDice = rand.nextDouble();
boolean WillTransmit = false;
boolean IsClose = false;
boolean IsntSame = false;
boolean IsSuscep = false;
IsSuscep = status[2]==1;
WillTransmit = RollDice >= InfectChance ;
IsntSame = ID != ThisID;
if(IsntSame==true && IsSuscep)
    IsClose = eaCoordApp.proxQuery(SX, SY);
if(IsClose)
     java.awt.Toolkit.getDefaultToolkit().beep();
//System.out.println("Agent responding is Agent" +ID +" With InfChc " +InfectChance);
//System.out.println("Conditions W,C,S,s[2] are " +WillTransmit +IsClose +IsntSame +IsSuscep);
if(WillTransmit && IsClose && IsntSame && IsSuscep){
    eaCoordApp.setSick((int)LengthInfect, ThisResistance);
//System.out.println("Infection Recieved");
}
//System.out.println("Sick Task Finished");


//#end_node:INGENIASCodeComponent7 <--- DO NOT REMOVE THIS

 }
 
}

 