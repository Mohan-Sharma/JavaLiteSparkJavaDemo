package org.msharma.domain.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author Mohan Sharma Created on 22/01/18.
 */
@Table("student")
public class Student extends Model
{
	private int rollNumber;
	private String firstName;
	private String lastName;

	public int getRollNumber()
	{
		rollNumber = Integer.parseInt(get("roll_number").toString());
		return rollNumber;
	}

	public void setRollNumber(int rollNumber)
	{
		this.rollNumber = rollNumber;
		set("roll_number", this.rollNumber);
	}

	public String getFirstName()
	{
		firstName = get("first_name").toString();
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
		set("first_name", this.firstName);
	}

	public String getLastName()
	{
		lastName = get("last_name").toString();
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
		set("last_name", this.lastName);
	}
}
