package com.vn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idCategory")
	private Integer id;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "trangthai")
	private Integer trangThai;
	
	@OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
	private List<Product> product;

	public Category() {
		super();
	}

	public Category(Integer id, String ten, Integer trangThai) {
		super();
		this.id = id;
		this.ten = ten;
		this.trangThai = trangThai;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	

}
