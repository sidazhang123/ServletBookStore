package cn.tf.dao;

import java.util.List;

import cn.tf.domain.Log;

public interface LogDao {
	void append(String mark, String bookid, String bookname, String code, String username, String timestamp);
	List<Log> findByMark(String mark);
	List<Log> findByCustomerCode(String code);
	List<Log> findAll();
	int recordNum();
	void cleanAll();
	 List<Log> findPageLog(int startIndex, int size);
}
