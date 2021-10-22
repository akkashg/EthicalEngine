import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stats {
		static DecimalFormat f = new DecimalFormat("0.00");
	     Map <String,Integer >savedStats=new HashMap<>();
	     Map <String,Integer >totalStats=new HashMap<>();
	     int savedHumans=0;
	     int savedHumansAgeSum=0;
	     public void SaveStatsUtil(Map <String,Integer >statsMap,List<Character> characters)
	     {
	    	 for(Character c:characters)
		     	{
		     		if(statsMap==savedStats)
		     		{
	     			if(c.getType().equals("human"))
	     			{
	     				savedHumans+=1;
	     				savedHumansAgeSum+=c.getAge();
	     			}
		     		}
		     		for(String characterstics:c.characterstics)
		     		{
		     			try
		     			{
		     				int current=statsMap.get(characterstics);
		     				statsMap.put(characterstics, current+1);
		     			}
		     			catch(Exception e)
		     			{
		     				statsMap.put(characterstics, 1);
		     			}
		     		}
		     	}
	     }
	     
	     public void SaveStats(Scenario scenario,String decision)
	     {
	    	 List<Character> saved=new ArrayList<Character>();
	    	if(decision.equals(Defaults.passenger))
	    	{
	    		saved=scenario.getPassengers();
	    	}
	    	else
	    	{
	    		saved=scenario.getPedestrians();
	    	}
	    	
	    	UpdateLegalStats(scenario,decision);
	    	SaveStatsUtil(savedStats,saved);
	    	SaveStatsUtil(totalStats,scenario.getPassengers());
	    	SaveStatsUtil(totalStats,scenario.getPedestrians());
	     }
	     
	     
	     
	     public void UpdateLegalStats(Scenario scenario,String decision)
	     {
	     	String characterstic;
	     	int value;
	     	List<Character> saved=new ArrayList<Character>();
	     	if(scenario.isLegalCrossing())
	     		characterstic="green";
	     	else
	     		characterstic="red";
	     	
	     	if(decision.equals(Defaults.passenger))
	     		saved=scenario.getPassengers();
	     	else
	     		saved=scenario.getPedestrians();
	     	
	     	try
     		{
     			value=savedStats.get(characterstic);
     			savedStats.put(characterstic, value+saved.size());
     					
     		}
     		catch(Exception e)
     		{
     			savedStats.put(characterstic, saved.size());
     		}
	     	
	     	try
	 		{
	 			value=totalStats.get(characterstic);
	 			totalStats.put(characterstic, value+scenario.getPedestriansLength()+scenario.getPassengersLength());
	 					
	 		}
	 		catch(Exception e)
	 		{
	 			totalStats.put(characterstic, scenario.getPedestriansLength()+scenario.getPassengersLength());
	 		}
	     	
	     }
	     public void ShowStatistics(int runs)
	     {
	    	 System.out.println("======================================\r\n"
	    	 		+ "# Statistic\r\n"
	    	 		+ "======================================\r\n"
	    	 		+ "- % SAVED AFTER "+runs+" RUNS");
	     	Map<String,Float> finalStats=new HashMap<>();
	     	for(Map.Entry<String, Integer> entry:totalStats.entrySet())
	     	{
	     		String key=entry.getKey();
	     		int val=entry.getValue();
	     		int saved;
	     		try
	     		{
	     			saved=savedStats.get(key);
	     		}
	     		catch(Exception e)
	     		{
	     			saved=0;
	     		}
	     		finalStats.put(key, (float) saved/val);
	     	}
	     	/*for(Map.Entry<String, Float> entry:finalStats.entrySet())
	     	{
	     		System.out.println(entry.getKey()+": "+entry.getValue());
	     	}*/
	     	
	     	SortHashmap.sortbykey(finalStats);
	     	float avg=(float)(savedHumansAgeSum/savedHumans);
	     	System.out.println("--\n"
	     			+ "average age: "+f.format(avg));
	     }
}
