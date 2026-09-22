
/* This File Creates a Window to Display the agent movement.
 * it can also output the movements to a file.
 * @author William R Moser <william.moser@temple.edu>
 *
 */



package ingenias.jade.components;
import jade.wrapper.StaleProxyException;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jade.core.*;



public class AgentWindowAppAppImp extends AgentWindowAppApp {
    public int agenttype = 1;
    public static Hashtable AgentArray = new Hashtable();
    public static Hashtable AgentCreate = new Hashtable();
    public static String filename = "data.txt";
    public static  FileOutputStream os = null;
    JFrame showAgentWindow =new JFrame();
    AgentPanel agentpanel;
    JPanel toppanel = new JPanel();
    JLabel msgL=new JLabel("");

            JPanel bottompanel = new JPanel();
            public static JTextField right1 = new JTextField("20");
            public static JTextField right2 = new JTextField("15");
            public static JTextField right3 = new JTextField("100");
            public static JTextField right4 = new JTextField("1");
            public static int agentID = 0;
            JLabel left1 = new JLabel("Infection Time in Hours");
            JLabel left2 = new JLabel("  % Infection Chance");
            JLabel left3 = new JLabel("  Simulation Time");
            JLabel left4 = new JLabel("   # of Agents");
            Component component3 = Box.createHorizontalStrut(10);
            Component component2 = Box.createHorizontalStrut(5);
            Component component4 = Box.createHorizontalStrut(5);
            JButton btn_stop = new JButton("Kill");                   // Stop Button
            JRadioButton radio_sick = new JRadioButton("Sick Agent");   // Start Button
            JRadioButton radio_healthy = new JRadioButton("Healthy Agent");   // Start Button
            ButtonGroup radio_group = new ButtonGroup();
            JButton btn_health = new JButton(" Deploy ");
            JPanel box_buttons = new JPanel();
            JPanel box_oldbuttons  = new JPanel();
            final JFileChooser fc = new JFileChooser();
           // BottomPanel bottompanel;
            int grIndex = 1;
            int AgentID = 0;


    public AgentWindowAppAppImp() throws FileNotFoundException{
        super();
        os = new FileOutputStream(filename);
    }


  public int getID(){
           AgentID++;
           AgentArray.put("Agent"+(AgentID), new AgentDot());
           System.out.println("Agent"+(AgentID));
          return(AgentID);
      }


      public void placeAgent(int AgentNumber, double AgentX, double AgentY, int State){
     //moved to previous method getID
          AgentDot ad = (AgentDot)  AgentArray.get("Agent"+(AgentNumber));
          if (ad != null){
              Random rand = new Random();
              ad.agentnumber = AgentNumber;
              ad.x = AgentX;
              ad.y = AgentY;
              ad.state = State;
              ad.initiated = true;
          }
      }


     public int[] getInputs(){
         int[] input = new int[4];
         String InfLen = right1.getText();
         String PercInf = right2.getText();
         String Time = right3.getText();
         input[0] = Integer.parseInt(InfLen);
         input[1] = Integer.parseInt(PercInf);
         input[2] = Integer.parseInt(Time);
         input[3] = agenttype;
         return(input);
     }

        public void showAgentWindow() {

           agentpanel = new AgentPanel();   //make a new JPanel
           agentpanel.setBorder(BorderFactory.createLineBorder(Color.black));
           createTopPanel();
           createBottomPanel();
           bottompanel.setSize(1020, 100);

           Container pane = showAgentWindow.getContentPane(); //container pane?
           pane.add( agentpanel, BorderLayout.CENTER ); //add the pane at the center of the window
           pane.add( toppanel, BorderLayout.NORTH);
           pane.add(bottompanel, BorderLayout.SOUTH);
           Timeren(); //wait 1000 ms before redrawing
           showAgentWindow.setSize(new Dimension(1020,700));
           showAgentWindow.setTitle(getOwner().getName()+" - Owned Movement Diagram");
           showAgentWindow.setResizable(false);
           showAgentWindow.setDefaultCloseOperation(showAgentWindow.EXIT_ON_CLOSE);
           showAgentWindow.doLayout();
	   showAgentWindow.setVisible(true);
       }


        public void createTopPanel()
        {
            toppanel = new JPanel();
            JButton save = new JButton("Save History");
            Color c = new Color(.6196f, .10588f, .2039f);
            toppanel.setBackground(c);
            msgL.setForeground(Color.white);
            toppanel.add(msgL);
            toppanel.add(save);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
                            int returnVal = fc.showSaveDialog(toppanel);
                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                              File file = fc.getSelectedFile();
                               }


                          } //end actionperformed
		}); //end actionlistener
            toppanel.setPreferredSize(new Dimension(50,50));


        }

        public void createBottomPanel()
        {
            bottompanel = new JPanel();
            Color c = new Color(.6196f, .10588f, .2039f);
            bottompanel.setBackground(c);
            right1.setColumns(4);
            right2.setColumns(4);
            right3.setColumns(4);
            right4.setColumns(4);
            radio_sick.setBackground(c);
            radio_sick.setForeground(Color.white);
            radio_healthy.setBackground(c);
            radio_healthy.setForeground(Color.white);
            radio_healthy.setSelected(true);
            left1.setForeground(Color.white);
            left2.setForeground(Color.white);
            left3.setForeground(Color.white);
            left4.setForeground(Color.white);
            jade.core.Runtime rt = jade.core.Runtime.instance();
            rt.setCloseVM(true);
            Profile p = new ProfileImpl();
            p.setParameter("preload","a*");
            p.setParameter(Profile.MAIN_PORT, "60000");
            p.setParameter(Profile.FILE_DIR, "jade/");

            radio_sick.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                agenttype = 2;
                }
            });
            radio_healthy.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                agenttype = 1;
                }
            });
            //Stop Button
            btn_stop.setEnabled(false);
            btn_stop.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   System.out.println("The Stop Button Action Performed");
                }
            });
         //Start Button


        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);{
          btn_health.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                // Create a new agent
                final jade.wrapper.AgentController agcCitizenAgent;
                try {
                        agentID++;
                        agcCitizenAgent = ac.createNewAgent("CitizenAgent"+agentID, "ingenias.jade.agents.CitizenAgentJADEAgent", new Object[0]);
                    new Thread(){
                        public void run(){
                            try {

                                 agcCitizenAgent.start();

                            } catch (Exception e){
                                e.printStackTrace();
                              }
                         }
                    }.start();
               } catch (StaleProxyException ex) {
                       // Logger.getLogger(BottomPanel.class.getName()).log(Level.SEVERE, null, ex);
                 }

          }    //end wrapper
          });  //end action performed
        }
        bottompanel.add(left1);
        bottompanel.add(right1);
        bottompanel.add(left2);
        bottompanel.add(right2);
        //bottompanel.add(left3);
       // bottompanel.add(right3);
        radio_group.add(radio_sick);
        radio_group.add(radio_healthy);
        bottompanel.add(radio_sick);
        bottompanel.add(radio_healthy);
        //bottompanel.add(left4);
       // bottompanel.add(right4);
        bottompanel.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder( 2, 2, 2, 2 ) ) );
        box_oldbuttons.add(component2);
        box_oldbuttons.add(component3);
       // box_oldbuttons.add(btn_stop);    //Stop
        box_oldbuttons.add(component4);
        box_oldbuttons.add(btn_health);
        box_oldbuttons.setSize(700, 25);
        box_oldbuttons.setBackground(c);
        bottompanel.add(box_oldbuttons);
       }


       public void Timeren(){  //seems like this is an Enable Timer
           int delay = 50;    //1000 ms delay (1 sec)

           ActionListener taskPerformer = new ActionListener() { //Add action listenter (still in Timeren)

               public void actionPerformed(ActionEvent evt) { //when the task (evt) is done
                   agentpanel.repaint(); //repaint the whole thing with current positions
                   int[] c = agentpanel.color;
                   msgL.setText("Number of Susceptible = " +c[0] +"  Number of Infectious = " +c[1] +"  Number of Removed = " +c[2] +"            ");
                   toppanel.repaint();
               }
           };
           new javax.swing.Timer(delay, taskPerformer).start();  //the Timer repeats the redraw on a delay
       }


   }


   class AgentPanel extends JPanel  {
       public int[] color = {0, 0, 0};
       public AgentPanel () {

          for ( int x = 0 ; x < AgentWindowAppAppImp.AgentArray.size() ; x++ ){ //set each element of the array to an agent
              AgentWindowAppAppImp.AgentArray.put("Agent"+(x+1), new AgentDot());

           }

           setPreferredSize (new Dimension(600,600));
       }

       protected void paintComponent (Graphics agents) {
           super.paintComponent (agents);   //paint the thing fed to it
           final ImageIcon icon = new ImageIcon("img/bg.png");
           agents.drawImage(icon.getImage(), 0, 0, 600, 600, null);
           color[0]=0;
           color[1]=0;
           color[2]=0;
           Random rand = new Random();
            for ( int x = 0 ; x < AgentWindowAppAppImp.AgentArray.size(); x++ ){
                //for all the dots, paint the with drawDots
                AgentDot ad = (AgentDot)  AgentWindowAppAppImp.AgentArray.get("Agent"+(x+1));
                if (ad != null){
                    if(ad.initiated==true){
                       ad.drawDots(agents);
                       if(ad.state==1){
                         color[0]++;
                       }
                       else if(ad.state==2){
                            color[1]++;
                       }
                       else if(ad.state==3){
                            color[2]++;
                       }

                    } //end chack for true
                    else if(ad.initiated==false){
                    }
                }
           }

        try {
            String tmp = color[0] + " " + color[1] + " " + color[2] + "\r\n";
            AgentWindowAppAppImp.os.write(tmp.getBytes());
            AgentWindowAppAppImp.os.flush();
        } catch (IOException ex) {
            Logger.getLogger(AgentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
   }






 