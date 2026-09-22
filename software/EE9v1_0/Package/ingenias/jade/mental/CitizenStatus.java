

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


package ingenias.jade.mental;

import java.util.*;
import ingenias.jade.components.*;
import ingenias.editor.entities.*;
import ingenias.editor.entities.ViewPreferences.ViewType;

public class CitizenStatus extends ingenias.editor.entities.RuntimeFact{
   
    double CitY;   
   
    int Sickness;   
   
    int Status;   
   
    double CitX;   
   
    double[][] Path;   
   
    int AgentID;   
   
    double PercInfect;   
   
    double LengthInfect;   
   
    double AgentResistance;   
    
   
  public CitizenStatus (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="CitizenStatus";
  }
  

  public CitizenStatus (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "CitizenStatus";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setCitY(double value){
     CitY=value;   
   };
   
   public double getCitY(){
     return CitY;      
   }
   

   public void setSickness(int value){
     Sickness=value;   
   };
   
   public int getSickness(){
     return Sickness;      
   }
   

   public void setStatus(int value){
     Status=value;   
   };
   
   public int getStatus(){
     return Status;      
   }
   

   public void setCitX(double value){
     CitX=value;   
   };
   
   public double getCitX(){
     return CitX;      
   }
   

   public void setPath(double[][] value){
     Path=value;   
   };
   
   public double[][] getPath(){
     return Path;      
   }
   

   public void setAgentID(int value){
     AgentID=value;   
   };
   
   public int getAgentID(){
     return AgentID;      
   }
   

   public void setPercInfect(double value){
     PercInfect=value;   
   };
   
   public double getPercInfect(){
     return PercInfect;      
   }
   

   public void setLengthInfect(double value){
     LengthInfect=value;   
   };
   
   public double getLengthInfect(){
     return LengthInfect;      
   }
   

   public void setAgentResistance(double value){
     AgentResistance=value;   
   };
   
   public double getAgentResistance(){
     return AgentResistance;      
   }
    
  
  
}

 