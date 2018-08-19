package ch4Projects;

//********************************************************************
public class Address {
	private String streetAddress, city, state;
	private long zipCode;
	
	public Address(){
		streetAddress = "";
		city = "";
		state = "";
		zipCode =0;

	}
	
	// -----------------------------------------------------------------
	// Sets up this Address object with the specified data.
	// -----------------------------------------------------------------
	public Address(String street, String town, String st, long zip) {
		streetAddress = street;
		city = town;
		state = st;
		zipCode = zip;
	}

	// -----------------------------------------------------------------
	// Returns this Address object as a string.
	// -----------------------------------------------------------------
	public String toString() {
		String result;
		result = streetAddress + "\n";
		result += city + ", " + state + " " + zipCode;
		return result;
	}
}