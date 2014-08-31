package com.lucasmro.restaurant.model;

public class Delivery {
	private String fullname;
	private String email;
	private String phone;
	private Address address;
	// TODO: Partner

	public Delivery() {
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Delivery [fullname=" + fullname + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + "]";
	}
}
