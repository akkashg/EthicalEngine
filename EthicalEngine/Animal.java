
public class Animal extends Character {
	
	String species;
	boolean isPet;
	public Animal()
	{
		this.setType("Animal");
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public boolean isPet() {
		return isPet;
	}
	public void setPet(boolean isPet) {
		this.isPet = isPet;
	}

}
