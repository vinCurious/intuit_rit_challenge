/* 
 * User.java 
 * 
 */

/**
 * This User class represents a particular user
 * 
 * @author Vinay Vasant More
 *
 */
public class User {

	int USERID;
	double totalExpenses;
	double riskyExpenses;
	double healthyExpenses;
	String struggle;
	double totalEarnings;

	// Default constructor
	User() {
		this.USERID = 0;
		totalExpenses = 0.0;
		riskyExpenses = 0.0;
		healthyExpenses = 0.0;
		this.struggle = "No";
		totalEarnings = 0.0;
	}

	// Constructor with parameters
	User(int id, double expenses, double risky, double healthy, String struggle, double earnings) {
		this.USERID = id;
		totalExpenses = expenses;
		riskyExpenses = risky;
		healthyExpenses = healthy;
		this.struggle = struggle;
		totalEarnings = earnings;
	}

	/**
	 * getUSERID function returns userid in integer format
	 *
	 * @param none
	 * 
	 * @return int
	 * 
	 */
	int getUSERID() {
		return USERID;
	}

	/**
	 * getExpenses function returns total expenses by a user in double format
	 *
	 * @param none
	 * 
	 * @return double
	 * 
	 */
	double getExpenses() {
		return totalExpenses;
	}

	/**
	 * getriskyExpenses function returns risky expenses by a user in double
	 * format
	 *
	 * @param none
	 * 
	 * @return double
	 * 
	 */
	double getriskyExpenses() {
		return riskyExpenses;
	}

	/**
	 * gethealthyExpenses function returns healthy expenses by a user in double
	 * format
	 *
	 * @param none
	 * 
	 * @return double
	 * 
	 */
	double gethealthyExpenses() {
		return healthyExpenses;
	}

	/**
	 * getRelationshipInfo function returns whether relationship struggle is
	 * faced or not by a user in String format
	 *
	 * @param none
	 * 
	 * @return String
	 * 
	 */
	String getRelationshipInfo() {
		return struggle;
	}

	/**
	 * getEarnings function returns total earnings by a user in double format
	 *
	 * @param none
	 * 
	 * @return double
	 * 
	 */
	double getEarnings() {
		return totalEarnings;
	}

}
