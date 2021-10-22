import java.util.ArrayList;
import java.util.List;

public class Character {
	
	int age;
	String type;
	String gender;
	String bodyType;
    List<String> characterstics=new ArrayList<String>();
	boolean pregnant;
	boolean isYou;
	String role;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.characterstics.add(type);
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if(!gender.equals(Defaults.gender) && this.getType().equals("human"))
			this.characterstics.add(gender);
		this.gender = gender;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		if(!bodyType.equals(Defaults.bodytype) && this.getType().equals("human"))
			this.characterstics.add(bodyType);
		this.bodyType = bodyType;
	}
	public boolean isPregnant() {
		return pregnant;
	}
	public void setPregnant(boolean pregnant) {
		if(pregnant)
			this.characterstics.add(Defaults.pregnant);
		this.pregnant = pregnant;
	}
	public boolean isYou() {
		return isYou;
	}
	public void setYou(boolean isYou) {
		if(isYou)
			this.characterstics.add(Defaults.you);
		this.isYou = isYou;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.characterstics.add(role);
		this.role = role;
	}
}
