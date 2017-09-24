package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Customer;

public interface CustomerDao {

	void save(Customer customer);

	void update(Customer customer);

	Customer findByCode(String code);

	Customer find(String username, String password);
	
	Customer findByUsername(String username);
	
	Customer findByEmail(String email);
	
	//HERE
	int getAllCusNum();
	List<Customer> listCustomers(int startIndex, int pagesize);


}
