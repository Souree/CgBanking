package com.cg.banking.beans;

public class Person {
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", aadharCard=" + aadharCard + ", panCard="
				+ panCard + ", mobileNumber=" + mobileNumber + "]";
	}
	private String firstName,lastName,aadharCard,panCard;
	private long mobileNumber;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aadharCard == null) ? 0 : aadharCard.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (mobileNumber ^ (mobileNumber >>> 32));
		result = prime * result + ((panCard == null) ? 0 : panCard.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (aadharCard == null) {
			if (other.aadharCard != null)
				return false;
		} else if (!aadharCard.equals(other.aadharCard))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobileNumber != other.mobileNumber)
			return false;
		if (panCard == null) {
			if (other.panCard != null)
				return false;
		} else if (!panCard.equals(other.panCard))
			return false;
		return true;
	}
	public Person(String firstName, String lastName, String aadharCard, String panCard, long mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.aadharCard = aadharCard;
		this.panCard = panCard;
		this.mobileNumber = mobileNumber;
	}
	
	
	
}