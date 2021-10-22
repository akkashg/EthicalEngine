import java.io.File;
import java.lang.Math;
import java.text.DecimalFormat;
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
    String log="ethicalengine.log";
    static boolean saveLog=false;
   
    static DecimalFormat f = new DecimalFormat("0.00");
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
    
    public static void MainMenu()
    {
    	Scanner keyboard = new Scanner(System.in);
    	WelcomeMessage();
    	List<Scenario> scenarios=readScenarios();
    	if(scenarios!=null)
    	{
    		System.out.println("{"+ scenarios.size() + "} Scenarios imported.");
    	}
    	while(true)
    	{
    	System.out.println("Please enter one of the following commands to continue:\n"
    			+ "- judge scenarios: [judge] or [j]\n"
    			+ "- run simulations with the in-built decision algorithm: [run] or [r]\n"
    			+ "- show audit from history: [audit] or [a]\n"
    			+ "- quit the program: [quit] or [q]\n"
    			+ "> ");
    	
    	String input=keyboard.next();
    	switch(input)
    	{
    	case ("judge"):
    	case ("j"):
    		judge(scenarios);
    		break;

    	case ("run"):
    	case ("r"):
    		RunSimulations(scenarios);
    		break;
 
    	}
    	}
    	
    }
    
    public static void judge(List<Scenario> scenarios)
    {
    	Stats judgeStats=new Stats();
    	Scanner keyboard = new Scanner(System.in);
    	List<String> savePassengers = new ArrayList<String> (List.of("1","passenger","passengers"));
    	List<String> savePedestrians = new ArrayList<String> (List.of("2","pedestrian","pedestrians"));
    	List<Scenario> generatedScenarios=new ArrayList<Scenario>();
    	List<String> decision=new ArrayList<String>();
    	TakeConsent();
    	int i=0;
    	String input="yes";
    	Scenario scenario;
    	while(input.equals("yes"))	
    	{
    		if(scenarios.size()==0)
    		{
    			scenario=RandomScenario.GetRandomScenario();
    			generatedScenarios.add(scenario);
    		}
    		else
    			scenario=scenarios.get(i);
    		printScenarios(scenario);
    		System.out.printf("Who should be saved? (passenger(s) [%d] or pedestrian(s) [%d])\n",scenario.getPassengersLength(),
    				scenario.getPedestriansLength());
    		String save=keyboard.next();
    		if(savePassengers.contains(save))
    		{
    			decision.add(savePassengers.get(2));
    			judgeStats.SaveStats(scenario,Defaults.passenger);
    		}
    		else
    		{
    			decision.add(savePedestrians.get(2));
    			judgeStats.SaveStats(scenario,Defaults.pedestrian);
    		}

    		
    		i=i+1;
    		
    		if(i%3==0 || (scenarios.size())==i)
    		{
    			judgeStats.ShowStatistics(i);
    		}
    		
    		System.out.println("Would you like to continue? (yes/no)");
    		input=keyboard.next();
    	}
    }
    
    public static void TakeConsent()
    {

    	Scanner keyboard = new Scanner(System.in);
    	System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
    	while(true)
    	{
    	String input=keyboard.next();
    	if(input.equals("yes"))
    	{
    		saveLog=true;
    		break;
    	}
    	else if(input.equals("no"))
    	{
    		saveLog=false;
    		break;
    	}
    	else
    	{
    		try
    	{
    			throw new InvalidInputException();
    	}
    		catch(Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    }
    }
    
    public static List<Scenario> readScenarios()
    {
    	
    	
    	List<Scenario> scenarios=new ArrayList<Scenario>();
    	try {
    		int line=0;
    		String [] dataArray;
    		File myObj = new File("F:\\JAVA -PROJECT\\Dilemma\\home\\"+"config.csv");
    		List<Character> passengers=new ArrayList<Character>();
    		List<Character> pedestrians=new ArrayList<Character>();
    		Scenario scenario=new Scenario();
    		Scanner myReader = new Scanner(myObj);
    	      while (myReader.hasNextLine()) {
    	    	  line=line+1;
    	          String data =myReader.nextLine();
    	          dataArray=data.split(",");
    	          
    	          
    	          if(dataArray.length==1 && (dataArray[0].equals("scenario:green") || dataArray[0].equals("scenario:red")))
    	          {
    	        	  if(!passengers.isEmpty() || !pedestrians.isEmpty())
    	        	  {
    	        		  scenario.setPassengers(passengers);
    	        		  scenario.setPedestrians(pedestrians);
    	        		  scenarios.add(scenario);
    	        		  passengers=new ArrayList<Character>();
    	        		  pedestrians=new ArrayList<Character>();
    	        		  }
    	        	  scenario=new Scenario();
    	        	  if(dataArray[0].equals("scenario:green"))
    	        		  scenario.setLegalCrossing(true);
    	        	  else if(dataArray[0].equals("scenario:red"))
    	        		  scenario.setLegalCrossing(false);
    	        		    
    	          }
    	          else if(dataArray.length<10 || dataArray.length>10)
    	          {
    	        	  System.out.println("WARNING: invalid data format in config file in line {"+line+"}");
    	          }
    	          else if(line!=1)
    	          {
    	            if(dataArray[0].equals("human"))
    	            {
    	              try 
						{
							Human h = getHuman(dataArray,line);
							if(h.getRole().equals("passenger"))
								passengers.add(h);
							else
								pedestrians.add(h);
						} 
    	              catch (Exception e) 
    	                {
    	            	
						}
    	            }
    	              else if(dataArray[0].equals("animal"))
    	              {
    	            	  try 
  						{
  							Animal a = getAnimal(dataArray,line);
  							if(a.getRole().equals("passenger"))
								passengers.add(a);
							else
								pedestrians.add(a);
						} 
  							
  						
      	              catch (Exception e) 
      	                {
      	            	 
  						}
    	              }
    	            
    	          }
    	      }
    	      myReader.close();
    	      scenario.setPassengers(passengers);
    		  scenario.setPedestrians(pedestrians);
    		  scenarios.add(scenario);
    	      return(scenarios);
    		}
    	catch(Exception ex)
    	{
    		System.out.print("");
    	}
    	return null;
    }
    
    public static void printScenarios(Scenario scenario)
    {
    		List<Character> passengers=scenario.getPassengers();
    		List<Character> pedestrians=scenario.getPedestrians();
    		
    		       System.out.println("======================================\n"
    		       		+ "# Scenario "+"\n"
    		       		+ "======================================");
    		       System.out.println("Legal crossing: "+(scenario.isLegalCrossing()? "yes" : "no") );
    		       System.out.println("Passengers ("+passengers.size()+")");
    		       for(Character c: passengers)
    		       {
    		    	  printCharacter(c);
    		       }
    		       
    		       System.out.println("Pedestrians ("+pedestrians.size()+")");
    		       
    		       for(Character c: pedestrians)
    		       {
    		    	   printCharacter(c);
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
    
    public static Human getHuman(String [] data,int line)
    {
    	try {
    	   if(data[0].equals("human"))
	       { 
	        	  
	        		  Human c=new Human();
	        		  c.setType("human");
	        		  
	        		  if(Characterstics.gender.contains(data[1]))
	        			  c.setGender(data[1]);
	        		  else
	        		  {
	        			  c.setGender("unknown");
	        			  ThrowInvalidCharacteristicException(line);
	        		  }
	        	  	  try
	        	  	  {
	        	  	  c.setAge(Integer.parseInt(data[2]));
	        	  	  }
	        	  	  catch(Exception e)
	        	  	  {
	        	  		  throw new NumberFormatException();
	        	  	  }
	        	  	  c.setAgeCategory();
	        	  	  
	        	  	  if(Characterstics.bodyType.contains(data[3]))
	        	  		  c.setBodyType(data[3]);
	        	  	  else
	        	  	  {
	        	  		c.setBodyType("unspecified");
	        	  		ThrowInvalidCharacteristicException(line);
	        	  	  }
	        	  	  
	        	  	  if(Characterstics.profession.contains(data[4]))
	        	  		  c.setProfession(data[4]);
	        	  	  else
	        	  	  {
	        	  		  c.setProfession("none");
	        	  	  }
	        	  	  c.setPregnant(computeBoolean(data[5]));
	        	  	  c.setYou(computeBoolean(data[6]));
	        	  	  c.setRole(data[9]);
	   	    	   return(c);
	        	  
	       }
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage()+"{"+line+"}");
    		return null;
    	}

    	   return null;
    }
    
    public static void ThrowInvalidCharacteristicException(int line)
    {
    	 try {
 	  		throw new  InvalidCharacteristicException();
 	  	  }
 	  	  catch(Exception e)
 	  	  {
 	  		System.out.println(e.getMessage()+"{"+line+"}");
 	  	  }
    }
    public static void printCharacter(Character c)
    {
    	 if(c.getType().equals("human"))
  	   {
  		   Human h=(Human) c;
  		   String s=h.getBodyType()+" "+h.getAgeCategory()+" "+h.getProfession()+" "+h.getGender();
  		   if(h.isYou)
  			   s="You "+s;
  		   if(h.isPregnant())
  			   s=s+" Pregnant";
  		   System.out.println("- "+s);
  	   }
  	   else
  	   {
  		   Animal a=(Animal) c;
  		   if(a.isPet)
  			   System.out.println("- "+a.getSpecies()+" is pet");
  		   else
  			   System.out.println("- "+a.getSpecies());
  		   
  	   }
    }
    public static Animal getAnimal(String [] data,int line)
    {
    	if(data[0].equals("animal"))
	       {
	    	   try {
	        		  Animal c=new Animal();
	        		  c.setType("animal");
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
	        	  System.out.print("getAnimal");
	        	  }

	       }
	       return(null);
    }
  
    public static void RunSimulations(List<Scenario> scenarios)
    {
    	int no;
    	Scanner keyboard=new Scanner(System.in);
    	Stats simulationStats=new Stats();
    	if(scenarios==null)
    	{
    		scenarios=new ArrayList<Scenario>();
    		System.out.println("How many scenarios should be run?");
    		while(true)
    		{
    		try
    		{
    		String input=keyboard.next();
    		no=Integer.parseInt(input);
    		break;
    		}
    		catch(Exception e)
    		{
    			System.out.println("Invalid input. How many scenarios should be run?");
    		}
    		}
    		
    		for(int i=0;i<no;i++)
    		{
    			Scenario scenario=RandomScenario.GetRandomScenario();
    			scenarios.add(scenario);
    		}
    	}
    	no=scenarios.size();
    	for(Scenario scenario:scenarios)
    	{
    		Decision decision=decide(scenario);
    		String d=decision.toString().toLowerCase();
    		simulationStats.SaveStats(scenario, d);
    	}
    	simulationStats.ShowStatistics(no);
    	System.out.println("That's all. Press Enter to return to main menu.");
    	keyboard.next();
    	return;
    }
    
    public static void main(String[] args) {
    	
    	MainMenu();
    	
    	
    }
}