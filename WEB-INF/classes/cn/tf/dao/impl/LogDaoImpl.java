package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tf.dao.LogDao;
import cn.tf.domain.Book;
import cn.tf.domain.Category;
import cn.tf.domain.Log;
import cn.tf.utils.C3P0Util;

public class LogDaoImpl implements LogDao {
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	
	@Override
	public void append(String mark, String bookid, String bookname, String code, String username, String timestamp) {
		try {
			Long num = (Long) qr.query("select count(mark) from log ", new ScalarHandler(1));
			qr.update("insert into log (logid,mark,bookid,bookname,code,username,timestamp) values(?,?,?,?,?,?,?)", (num+1),mark, bookid,bookname, code,username, timestamp);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Log> findByMark(String mark) {
		try {
			return qr.query("select * from log where mark=?", new BeanListHandler<Log>(Log.class), mark);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public List<Log> findByCustomerCode(String code) {
		try {
			
			return qr.query("select * from log where code=?", new BeanListHandler<Log>(Log.class), code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public int recordNum() {
		try {
			Long num = (Long) qr.query("select count(mark) from log ", new ScalarHandler(1));
			return num.intValue();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Log> findAll() {
		try {
			return qr.query("select * from log ", new BeanListHandler<Log>(Log.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void cleanAll() {
		try {
			qr.update("delete from log");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Log> findPageLog(int startIndex, int size) {
	
		try {
			List<Log>  logs=qr.query("select * from log limit ?,? ",new BeanListHandler<Log>(Log.class),startIndex,size);
			
			return logs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
