package models;

import observers.CustomerObserverable;

public class Customer implements CustomerObserverable {
	private final String defaultName = "Blank";

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
		if(phone.length() > 10 || phone.length() < 9)
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
		if(name.isBlank())
			name = defaultName; 
		else {
			for(int i = 0 ; i < name.length(); i++) {
				if(!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i)))
					throw new Exception("Invalid Customer name:\nName must contains only letters");
			}
			this.name = name;
		}
	}
 
	@Override
	public String toString() {
		return "Customer name: " + name.toUpperCase() + " Phone number: " + phone;
	}

	@Override
	public String update() {
		return name + " updated";
	}
}
