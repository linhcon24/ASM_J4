package com.vn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account implements Serializable{
	@Id
	@Column(name = "username" , nullable = false)
	private String username ;
	@Column(name = "password")
	private String password ;
	@Column(name = "email")
	private String email ;
	@Column(name = "role")
	private Integer role ;
	
	@OneToMany(mappedBy = "username" , fetch = FetchType.LAZY)
	private List<HoaDon> hoaDons;
	@OneToMany(mappedBy = "username" , fetch = FetchType.LAZY)
	private List<Cart> carts;
	public Account() {
		super();
	}
	public Account(String username, String password, String email, Integer role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public List<HoaDon> getHoaDons() {
		return hoaDons;
	}
	public void setHoaDons(List<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	
	
	
	
	
	
}