
public class Human extends Character {
	
	String profession;
	
	String ageCategory;
	public Human()
	{
		this.setType("human");
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
			this.profession = profession;
			if(!profession.equals(Defaults.profession))
				this.characterstics.add(profession);
	}
	public String getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory() {
		if(this.getAge()>68)
			this.ageCategory = "senior";
		else if(this.getAge()>17)
			this.ageCategory = "adult";
		else if(this.getAge()>4)
			this.ageCategory = "child";
		else if(this.getAge()>=0)
			this.ageCategory = "baby";
		this.characterstics.add(ageCategory);
	}

}
