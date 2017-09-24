package cn.tf.dao;

import java.sql.SQLException;
import java.util.List;

import cn.tf.domain.Book;
import cn.tf.domain.Entity;
import cn.tf.domain.Graph;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GraphDao {
	
	void writeEntities(List<Book> booklist) throws SQLException;
	List<JSONArray> generateJSON() throws SQLException;
//	void saveGraph(Graph graph);
//	
//	
//	List<Graph> findAllBySubject(String subject);
//	
//	List<Graph> findAllByObject(String Object);
//	
//	Entity getVenueLabel(String subject);
//	
//	Entity getPaperTitle(String subject);
//	
//	Entity getAuthorOrVenue(String subject);

}
