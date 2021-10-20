import java.io.File;
import java.lang.Math;
import java.util.Scanner;
import java.util.*;


/**
 * COMP90041, Sem2, 2021: Final Project
 * @author: 
 * student id: 
 * student email: 
 */
public class EthicalEngine {

    public enum Decision {PASSENGERS, PEDESTRIANS};

    /**
     * Decides whether to save the passengers or the pedestrians
     * @param Scenario scenario: the ethical dilemma
     * @return Decision: which group to save
     */
    public static Decision decide(Scenario scenario) {
        // a very simple decision engine
        // TODO: take into account at least 5 characteristics
        
        // 50/50
        if(Math.random() > 0.5) {
            return Decision.PEDESTRIANS;
        } else {
            return Decision.PASSENGERS;
        }
    }

    /**
     * Program entry
     */
    public static void WelcomeMessage()
    {
    	try {
    		int line=0;
    		String [] dataArray;
    		File myObj = new File("F:\\JAVA -PROJECT\\Dilemma\\home\\"+"welcome.ascii");
    		Scanner myReader = new Scanner(myObj);
    	      while (myReader.hasNextLine()) {
    	          String data =myReader.nextLine();
    	          System.out.println(data);
    	      }
    		}
    	catch(Exception ex)
    	{
    		System.out.print("");
    	}
    }
    
    public static void readScenerios()
    {
    	
    	
    	List<Scenario> scenarios=new ArrayList<Scenario>();
    	try {
    		int line=0;
    		String [] dataArray;
    		File myObj = new File("F:\\JAVA -PROJECT\\Dilemma\\home\\"+"config.csv");
    		Scanner myReader = new Scanner(myObj);
    	      while (myReader.hasNextLine()) {
    	    	  line=line+1;
    	          String data =myReader.nextLine();
    	          dataArray=data.split(",");
    	          
    	          
    	          if(dataArray.length==1 && (dataArray[0].equals("scenario:green") || dataArray[0].equals("scenario:red")))
    	          {
    	        	  List<Character> characters=new ArrayList<Character>();
    	        	  Scenario scenario=new Scenario();
    	        	  if(dataArray[0].equals("scenario:green"))
    	        		  scenario.setLegalCrossing(true);
    	        	  else if(dataArray[0].equals("scenario:red"))
    	        		  scenario.setLegalCrossing(false);
    	        		    
    	          }
    	          else if(dataArray.length<10 || dataArray.length>10)
    	          {
    	        	  System.out.println("WARNING: invalid data format in config file in line {"+line+"}");
    	          }
    	          else
    	          {
    	         
    	          }
    	      }
    		}
    	catch(Exception ex)
    	{
    		System.out.print("");
    	}
    }
    public static boolean computeBoolean(String a)
    {
    	a=a.toLowerCase();
    	if(a.equals("true"))
    		return(true);
    	else if(a.equals("false"))
    		return(false);
    	return(true);
    }
    public static Character getCharacter(String [] data)
    {
	       if(data[0].equals("human"))
	       { 
	        	  try {
	        		  Human c=new Human();
	        	  	  c.setGender(data[1]);
	        	  	  c.setAge(Integer.parseInt(data[2]));
	        	  	  c.setBodyType(data[3]);
	        	  	  c.setProfession(data[4]);
	        	  	  c.setPregnant(computeBoolean(data[5]));
	        	  	  c.setYou(computeBoolean(data[6]));
	        	  	  c.setRole(data[9]);
	   	    	   return(c);
	        	  }
	        	catch(Exception e)
	        	  {
	        	  System.out.print("");
	        	  }
	       }
	       else if(data[0].equals("animal"))
	       {
	    	   try {
	        		  Animal c=new Animal();
	        	  	  c.setGender(data[1]);
	        	  	  c.setAge(Integer.parseInt(data[2]));
	        	  	  c.setPregnant(computeBoolean(data[5]));
	        	  	  c.setYou(computeBoolean(data[6]));
	        	  	  c.setSpecies(data[7]);
	        	  	  c.setPet(computeBoolean(data[8]));
	        	  	  c.setRole(data[9]);
	   	    	   return(c);
	        	  }
	        	catch(Exception e)
	        	  {
	        	  System.out.print("");
	        	  }

	       }
	       return(null);
	       
    }
    
    public static void main(String[] args) {
    	
    	//WelcomeMessage();
    	readScenerios();
    	
    }
}