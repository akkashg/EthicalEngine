
import java.util.*;
public class RandomScenario {
	static String [] character= {"human","animal"};
	static String [] gender= {"male","female","unknown"};
	static String [] species= {"cat","dog","bird"};
	static boolean [] b= {true,false};
	static String [] bodyType= {"average","athletic","overweight","unspecified"};
	static String [] profession= {"none","doctor","ceo","unemployed","homeless","criminal"};
	static private List<Character> passengers=new ArrayList<Character>();
	static private List<Character> pedestrians=new ArrayList<Character>();
	public static Scenario GetRandomScenario()
	{
		Scenario scenario=new Scenario();
		int noOfPassengers=randomInt(60)+1;
		int noOfPedestrians=randomInt(60)+1;
		for(int i=0;i<noOfPassengers;i++)
		{
		int chooseCharacter=randomInt(2);
		if(chooseCharacter==0)
		{
			Human h=getRandomHuman();
			h.setRole("passenger");
			passengers.add(h);
		}
		else
		{
			Animal a=getRandomAnimal();
			a.setRole("passenger");
			passengers.add(a);
		}
		}
		
		for(int i=0;i<noOfPedestrians;i++)
		{
		int chooseCharacter=randomInt(2);
		if(chooseCharacter==0)
		{
			Human h=getRandomHuman();
			h.setRole("pedestrians");
			pedestrians.add(h);
		}
		else
		{
			Animal a=getRandomAnimal();
			a.setRole("pedestrians");
			pedestrians.add(a);
		}
		}
		scenario.setPassengers(passengers);
		scenario.setPedestrians(pedestrians);
		scenario.setLegalCrossing(b[randomInt(2)]);
		return(scenario);
	}

	public static Animal getRandomAnimal()
	{
		Animal a=new Animal();
		a.setGender(gender[randomInt(3)]);
		a.setSpecies(species[randomInt(3)]);
		a.setPet(b[randomInt(2)]);
		a.setAge(randomInt(100));
		return(a);
	}
	public static Human getRandomHuman()
	{
		Human h=new Human();
		h.setGender(gender[randomInt(3)]);
		h.setAge(randomInt(100));
		h.setAgeCategory();
		h.setBodyType(bodyType[randomInt(4)]);
		h.setProfession(profession[randomInt(6)]);
		if(h.getGender().equals("female") && h.getAgeCategory().equals("adult"))
			h.setPregnant(b[randomInt(2)]);
		return(h);
	}
	public static int randomInt(int i)
	{
		Random rand=new Random();
		return(rand.nextInt(i));
	}
}
