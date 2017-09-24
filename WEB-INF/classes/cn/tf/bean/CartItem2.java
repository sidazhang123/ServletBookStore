package cn.tf.bean;

import java.io.Serializable;
import java.util.List;

import cn.tf.domain.OrderItem;

public class CartItem2 implements Serializable{
	private String id;
	private String name;
	private float price;
	private String number;
	private String code;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
