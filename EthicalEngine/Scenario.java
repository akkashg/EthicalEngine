/**
 * Represents a scenario to decide on
 * COMP90041, Sem2, 2021: Final Project
 * @author: 
 * student id: 
 * student email: 
 */
import java.util.*;
public class Scenario {
	
	private List<Character> passengers=new ArrayList<Character>();
	

	private List<Character> pedestrians=new ArrayList<Character>();
	private boolean legalCrossing;
	
	public Scenario()
	{
		
	}
	public List<Character> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Character> passengers) {
		this.passengers = passengers;
	}

	public List<Character> getPedestrians() {
		return pedestrians;
	}

	public void setPedestrians(List<Character> pedestrians) {
		this.pedestrians = pedestrians;
	}

	public boolean isLegalCrossing() {
		return legalCrossing;
	}

	public void setLegalCrossing(boolean legalCrossing) {
		this.legalCrossing = legalCrossing;
	}

}
