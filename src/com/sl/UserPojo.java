package com.sl;

public class UserPojo {
	/**
	 * @uml.property name="id"
	 */
	String id;
	/**
	 * @uml.property name="email"
	 */
	String email;
	/**
	 * @uml.property name="verified_email"
	 */
	boolean verified_email;
	/**
	 * @uml.property name="name"
	 */
	String name;
	/**
	 * @uml.property name="given_name"
	 */
	String given_name;
	/**
	 * @uml.property name="family_name"
	 */
	String family_name;

	/**
	 * @return
	 * @uml.property name="id"
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 * @uml.property name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property name="email"
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 * @uml.property name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 * @uml.property name="verified_email"
	 */
	public boolean isVerified_email() {
		return this.verified_email;
	}

	/**
	 * @param verified_email
	 * @uml.property name="verified_email"
	 */
	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

	/**
	 * @return
	 * @uml.property name="name"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 * @uml.property name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property name="given_name"
	 */
	public String getGiven_name() {
		return this.given_name;
	}

	/**
	 * @param given_name
	 * @uml.property name="given_name"
	 */
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	/**
	 * @return
	 * @uml.property name="family_name"
	 */
	public String getFamily_name() {
		return this.family_name;
	}

	/**
	 * @param family_name
	 * @uml.property name="family_name"
	 */
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String toString() {
		return

		"UserPojo [id=" + this.id + ", email=" + this.email + ", verified_email=" + this.verified_email + ", name=" + this.name
				+ ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
	}
}
