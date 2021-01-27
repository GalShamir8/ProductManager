package models;

import observers.CustomerObserverable;

public class Customer implements CustomerObserverable{

	private String name;
	private String phone;
	private boolean isPromoted;
	
	/**
	 * 
	 * @param name
	 * @param phone
	 * @param isPromoted
	 * @throws Exception
	 */
	public Customer(String name, String phone, boolean isPromoted) throws Exception {
		setName(name);
		setPhone(phone);
		this.isPromoted = isPromoted;
	}
		

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isPromoted() {
		return isPromoted;
	}
	/**
	 * 
	 * @param phone
	 * @throws Exception
	 */
	private void setPhone(String phone) throws Exception {
		if(phone.length() < 10 || phone.length() < 9)
			throw new Exception("Illegal phone number:\nphone must contains 9-10 digits");
		try {
			Integer.parseInt(phone);
			this.phone = phone;
		}
		catch(Exception e)
		{
			throw new Exception("Illegal phone number:\nphone must contains only digits");
		}
	}
	/**
	 * 
	 * @param name
	 * @throws Exception
	 */
	private void setName(String name) throws Exception {
		if(!(name.chars().allMatch(Character::isLetter)))
			throw new Exception("Invalid name:\nName must contains only letters");
		else
			this.name = name;
	}
	
	@Override
	public String toString() {
		return "Customer: " + name + "\tPhone number: " + phone;
	}


	@Override
	public String update() {
		return name + " updated";
	}
}
