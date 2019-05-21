package miniPhoneBook;

public class PhoneItem {

	public String name;
	public String teleNum;
	
	public PhoneItem() {

	}
	
	public PhoneItem(String name, String teleNum) {
		super();
		this.name = name;
		this.teleNum = teleNum;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTeleNum() {
		return teleNum;
	}
	
	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}
	
	public String toString() {
		return "[" + this.name + "]" + this.teleNum;
	}
	
	
}
