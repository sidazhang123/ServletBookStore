package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tf.dao.CustomerDao;

import cn.tf.domain.Customer;
import cn.tf.utils.C3P0Util;

public class CustomerDaoImpl implements CustomerDao {

	private QueryRunner  qr=new QueryRunner(C3P0Util.getDataSource());
	
	public Customer findByUsername(String username){
		try {
			return qr.query("select * from customers where username=?", new BeanHandler<Customer>(Customer.class),username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Customer findByEmail(String email){
		try {
			return qr.query("select * from customers where email=?", new BeanHandler<Customer>(Customer.class),email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void save(Customer customer) {
		try {
			qr.update("insert into customers (id,username,password,phone,address,email,code,actived,firstname,lastname,creditcardno,"
					+ "nickname,yearofbirth,isbanned) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
					customer.getId(),customer.getUsername(),customer.getPassword(),customer.getPhone(),customer.getAddress(),
					customer.getEmail(),customer.getCode(),customer.isActived(),customer.getFirstname(),customer.getLastname(),
					customer.getCreditcardno(),customer.getNickname(),customer.getYearofbirth(),customer.getIsbanned());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void update(Customer customer) {
		try {
			qr.update("update customers set username=?,password=?,phone=?,address=?,email=?,code=?,actived=?,firstname=?, "
					+ "lastname=?, creditcardno=?, nickname=?, yearofbirth=?, isbanned=? where id=?", 
					customer.getUsername(),customer.getPassword(),customer.getPhone(),customer.getAddress(),
					customer.getEmail(),customer.getCode(),customer.isActived(),customer.getFirstname(),customer.getLastname(),customer.getCreditcardno()
					,customer.getNickname(),customer.getYearofbirth(),customer.getIsbanned(),customer.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Customer findByCode(String code) {
		try {
			return qr.query("select * from customers where code=?", new BeanHandler<Customer>(Customer.class),code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	
	//HERE
	public int getAllCusNum(){
		try {
			Long num=(Long) qr.query("select count(id) from customers ",new ScalarHandler(1));
			return num.intValue();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//HERE
	public List<Customer> listCustomers(int startIndex, int size) {
	
		try {
			List<Customer>  customers=qr.query("select * from customers limit ?,? ",new BeanListHandler<Customer>(Customer.class),startIndex,size);
			
			return customers;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Customer find(String username, String password) {
		try {
			return qr.query("select * from customers where username=? and password=?", new BeanHandler<Customer>(Customer.class),username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
