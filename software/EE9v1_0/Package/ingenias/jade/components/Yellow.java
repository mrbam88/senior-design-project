package ingenias.jade.components;
/** Some Utils for Yellow pages **/
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.exception.*;
import ingenias.jade.mental.*;
import java.util.*;


public class Yellow {
	public static List<String> getIds(YellowPages yp, String rolename){
 		List list = new LinkedList<String>();
		 //	 Find the agents playing the role "Expert"
	     jade.domain.FIPAAgentManagement.DFAgentDescription[] experts;
	     jade.domain.FIPAAgentManagement.DFAgentDescription desc;
	     jade.core.AID aid;
	     String id;
	     try{      	
	     	//Code similar to experts= yp.getAgents("Expert");
	     		// build the descriptor
	     	jade.domain.FIPAAgentManagement.DFAgentDescription dfd;
	     	jade.domain.FIPAAgentManagement.ServiceDescription sd;
	 		dfd = new jade.domain.FIPAAgentManagement.DFAgentDescription();
	 		sd = new jade.domain.FIPAAgentManagement.ServiceDescription();
	 		sd.setType(rolename);
	 		dfd.addServices(sd); 
	 		// run the search
	 		jade.domain.FIPAAgentManagement.SearchConstraints searchcons 
	 			= new jade.domain.FIPAAgentManagement.SearchConstraints();
	 		searchcons.setMaxDepth(5l); // To search within federated DFs
	 		searchcons.setMaxResults(10l); // To search several results
	 		experts= jade.domain.DFService.search(yp.ja, dfd,searchcons);
	 	// End of the code similar to experts = ...
	     	
	 		//System.out.print("Yellow Pages for "+rolename+" role. Ids=");
	 		int num_experts = experts.length;	     	
	 		for(int i=0;i<num_experts;i++){
	     		desc=experts[i];
	     		aid = desc.getName();
	     		id = aid.getLocalName();
	     		//expertsId[i]=id;
	     		list.add(id);
	     	}
	     	//System.out.println();
	     	
	     }catch(jade.domain.FIPAException e){
	    		System.out.println(e.getMessage());
	    		e.printStackTrace();
	      }
	     return list;

	}
	
	public static void println(YellowPages yp, String rolename){		
		List<String>list = getIds(yp,rolename);
		Iterator<String> it = list.iterator();
		System.out.print("Yellow Pages for "+rolename+" role. Ids=");		
		while(it.hasNext()){
			String id=it.next();
			System.out.print(id+",");			
		}
		System.out.println();

	}
	
	public static void println(RuntimeConversation conv){
		System.out.print("Collaborators =");
		Enumeration cols = conv.getCollaboratorsElements();
		while(cols.hasMoreElements()){
			System.out.print(cols.nextElement().toString()+",");
		}
		System.out.println();
	}
	
	/** Adds to the conversation all the Agents of a Role name  **/
	public static void addAll(YellowPages yp, RuntimeConversation conv, 
			String rolename){
		List<String> ids = getIds(yp,rolename);
		Iterator<String> it = ids.iterator();
		while(it.hasNext()){
			String id = it.next();
			conv.addCollaborators(id);			
		} 
	}
	/** Adds to the conversation all the Agents of a Role name But one. Return the number 
	 * of collaborators.
	 *  **/
	public static int addAllButOne(YellowPages yp, RuntimeConversation conv, 
			String rolename,String oneId){
		int numCollab = 0;
		List<String> ids = getIds(yp,rolename);
		Iterator<String> it = ids.iterator();
		//System.out.println("meId="+meId);
		//System.out.print("colab=");
		while(it.hasNext()){
			String id = it.next();
			if(!id.equals(oneId)){
				//System.out.print(id+",");
				conv.addCollaborators(id);
				numCollab++;
			}
		} 
		//System.out.println();
		return numCollab;
	}
	/** Adds to the conversation all the Agents of a Role name. Ratio param 
	 * indicates the probability of adding each agent. At least, one 
	 * collaborator is always added. **/
	public static void addSome(YellowPages yp, RuntimeConversation conv, 
			String rolename, Double ratio){
		int numAdded = 0;
		Random generator= new Random();
		List<String> ids = getIds(yp,rolename);
		Iterator<String> it = ids.iterator();
		while(it.hasNext()){
			String id = it.next();
			if(generator.nextDouble()<ratio){
				conv.addCollaborators(id);
				numAdded++;
			}
		}
		
		// if no collaborator has been added, then add one collaborator.
		if(numAdded==0){
			int numAgents = ids.size();
			Double aux = generator.nextDouble()*numAgents;
			int index = aux.intValue();
			conv.addCollaborators(ids.get(index));
		}		
	}
	
	/** Add the  most similar agent to a given agent. If possible,
	 * with the last number, with the same 
	 */
	 public static void addMostSimilarToOne(YellowPages yp, RuntimeConversation conv, 
				String rolename, String idOne){
		 	/** Get the number of idOne */
		 	String[] auxSplit = idOne.split("_");
		    String numberAgentOne = auxSplit[auxSplit.length-1];
		    //System.out.println("numberAgentOne="+numberAgentOne);
		 
		    /** Add the the agent with the same number **/
		 	int numAdded = 0;
			Random generator= new Random();
			List<String> ids = getIds(yp,rolename);
			Iterator<String> it = ids.iterator();
			while(it.hasNext()){
				String id = it.next();
				auxSplit=id.split("_");
				String number = auxSplit[auxSplit.length-1];
				if(numberAgentOne.equals(number)){
					conv.addCollaborators(id);
					numAdded++;
				}
			}
			
			// if no collaborator has been added, then add one collaborator.
			if(numAdded==0){
				int numAgents = ids.size();
				Double aux = generator.nextDouble()*numAgents;
				int index = aux.intValue();
				conv.addCollaborators(ids.get(index));
			}		
		}
		
	 
	

}
