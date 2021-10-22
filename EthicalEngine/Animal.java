
public class Animal extends Character {
	
	String species;
	boolean isPet;
	public Animal()
	{
		this.setType("animal");
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.characterstics.add(species);
		this.species = species;
	}
	public boolean isPet() {
		return isPet;
	}
	public void setPet(boolean isPet) {
		if(isPet)
			this.characterstics.add(Defaults.pet);
		this.isPet = isPet;
	}

}
