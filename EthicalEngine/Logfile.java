import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logfile {
	
	public String folder="";
	
	public static void write(Scenario scenario,String decision)
	{
		String line;
		File myFoo = new File("log.txt");
		try {
		FileOutputStream fooStream = new FileOutputStream(myFoo, true); // true to append
		// false to overwrite.
		if(scenario.isLegalCrossing())
			line=Defaults.green.getBytes()+","+decision+"\n";
		else
			line=Defaults.red.getBytes()+","+decision+"\n";
		fooStream.write(line.getBytes());
		
		for(Character c:scenario.getPassengers())
		{
			if(c.getType().equals(Defaults.human))
			{
				Human h=(Human) c;
				line=getHumanInString(h);
			}
			else
			{
				Animal a=(Animal) c;
				line=getAnimalInString(a);
			}
			fooStream.write(line.getBytes());
		}
		
		for(Character c:scenario.getPedestrians())
		{
			if(c.getType().equals(Defaults.human))
			{
				Human h=(Human) c;
				line=getHumanInString(h);
			}
			else
			{
				Animal a=(Animal) c;
				line=getAnimalInString(a);
			}
			fooStream.write(line.getBytes());
		}
		fooStream.close();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: could not print results. Target directory does not exist.");
		}
	}

	public static String getHumanInString(Human h)
	{
		String line;
		line=Defaults.human+",";
		line+=h.getGender()+",";
		line+=h.getAge()+",";
		line+=h.getBodyType()+",";
		line+=h.getProfession()+",";
		line+=h.isPregnant()+",";
		line+=h.isYou()+",,,";
		line+=h.getRole();
		return(line+"\n");
	}
	
	public static String getAnimalInString(Animal a)
	{
		String line;
		line=Defaults.animal+",";
		line+=a.getGender()+",";
		line+=a.getAge()+",";
		line+=",";
		line+=",";
		line+=a.isPregnant()+",";
		line+=a.isYou()+",";
		line+=a.getSpecies()+",";
		line+=a.isPet()+",";
		line+=a.getRole();
		return(line+"\n");
	}
	
	  public static List<Scenario> readScenarios()
	    {
	    	List<Scenario> scenarios=new ArrayList<Scenario>();
	    	try {
	    		int line=0;
	    		String [] dataArray;
	    		File myObj = new File("log.txt");
	    		List<Character> passengers=new ArrayList<Character>();
	    		List<Character> pedestrians=new ArrayList<Character>();
	    		Scenario scenario=new Scenario();
	    		Scanner myReader = new Scanner(myObj);
	    	      while (myReader.hasNextLine()) {
	    	    	  line=line+1;
	    	          String data =myReader.nextLine();
	    	          dataArray=data.split(",");
	    	         
	    	          if(dataArray.length==2 && (dataArray[0].equals(Defaults.green) || dataArray[0].equals(Defaults.red)))
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
	    	        	  if(dataArray[0].equals(Defaults.green))
	    	        		  scenario.setLegalCrossing(true);
	    	        	  else if(dataArray[0].equals(Defaults.red))
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
								Human h = EthicalEngine.getHuman(dataArray,line);
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
	  							Animal a = EthicalEngine.getAnimal(dataArray,line);
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
	    
}
