
public class Human extends Character {
	
	String profession;
	
	String ageCategory;
	public Human()
	{
		this.setType("Human");
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}

}
