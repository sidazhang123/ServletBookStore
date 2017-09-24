package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.tf.bean.Edge;
import cn.tf.bean.Node;
import cn.tf.bean.connBook;
import cn.tf.bean.vEdge;
import cn.tf.bean.vNode;
import cn.tf.dao.GraphDao;
import cn.tf.domain.Book;

import cn.tf.utils.C3P0Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GraphDaoImpl implements GraphDao {
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	private Map<String, connBook> AtoP = new HashMap<String, connBook>();
	private Map<String, connBook> VtoP = new HashMap<String, connBook>();

	public void writeEntities(List<Book> booklist) throws SQLException {
		qr.update("delete from entity_store");//clean 2 tables before writing into.
		qr.update("delete from graph_store");
		for (int i = 0; i < booklist.size(); i++) {
			
			Book b = booklist.get(i);
			System.out.println(b.getCategory());
			String name = b.getName();
			String auth = b.getAuthor();
			String venue = b.getVenue();
			System.out.println(b.getCategory().getName());
			
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "P" + (i+1), "Type",
					b.getCategory().getName());
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "P" + (i+1), "class",
					"entityNode");
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "P" + (i+1), "Title", name);

			if (auth != null) {
				if (AtoP.containsKey(auth)) {
					connBook tmp = AtoP.remove(auth);
					tmp.add(Integer.toString(i+1));
					AtoP.put(auth, tmp);
				} else {
					connBook ept = new connBook();
					ept.add(Integer.toString(i+1));
					AtoP.put(auth, ept);
				}
			}
			if (venue != null) {
				if (VtoP.containsKey(venue)) {
					connBook tmp = VtoP.remove(venue);
					tmp.add(Integer.toString(i+1));
					VtoP.put(venue, tmp);
				} else {
					connBook ept = new connBook();
					ept.add(Integer.toString(i+1));
					VtoP.put(venue, ept);
				}
			}

		}
		int Ai = 0, Ei = 0, Vi=0;
		for (Map.Entry<String, connBook> m : AtoP.entrySet()) {
			connBook p = m.getValue();
			String nodeA = m.getKey();
			Ai++;
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "A" + Ai, "Type", "Author");
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "A" + Ai, "class",
					"entityNode");
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "A" + Ai, "Name", nodeA);

			for (int i = 0; i < p.getSize(); i++) {
				Ei++;
				String Pi = p.get(i); // A(Ai) -- E(Ei) -- P(Pi) ~graphstore
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "Type", "directedLink");
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "class",
						"Edge");
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "Label", "authoredBy");
				qr.update("insert into graph_store(subject,predicate,object) values (?,?,?)", "P" + Pi, "E"+Ei, "A"+Ai);
			}
		}
		for (Map.Entry<String, connBook> m : VtoP.entrySet()) {
			connBook p = m.getValue();
			String nodeV = m.getKey();
			Vi++;
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "V" + Vi, "Type", "Venue");
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "V" + Vi, "class",
					"entityNode");
			qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "V" + Vi, "Name", nodeV);

			for (int i = 0; i < p.getSize(); i++) {
				Ei++;
				String Pi = p.get(i); // A(Ai) -- E(Ei) -- P(Pi) ~graphstore
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "Type", "directedLink");
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "class",
						"Edge");
				qr.update("insert into entity_store(subject,predicate,object) values (?,?,?)", "E" + Ei, "Label", "publishedIn");
				qr.update("insert into graph_store(subject,predicate,object) values (?,?,?)", "P" + Pi, "E"+Ei, "V"+Vi);
			}
		}

	}
	
	public List<JSONArray> generateJSON() throws SQLException{
		
	
			List<Node> nodes = qr.query("select * from entity_store where predicate=? OR predicate=?", new BeanListHandler<Node>(Node.class), "Title","Name");
			List<Edge> edges = qr.query("select * from graph_store ", new BeanListHandler<Edge>(Edge.class));
		
			int id=0;
			Map<String, Integer> getId=new HashMap<String,Integer>();
			ArrayList<vNode> vnodes=new ArrayList<vNode>();
			ArrayList<vEdge> vedges=new ArrayList<vEdge>();
			for(Node n:nodes){
				id++;
				getId.put(n.getSubject(),id);
				vNode vn = new vNode();
				vn.setId(id);
				vn.setLabel(n.getSubject()+"\n"+n.getObject());
				String sub=n.getObject();
				if(sub.startsWith("P")){
					vn.setShape("box");
				}else if(sub.startsWith("A")){
					vn.setShape("square");
				}else{
					vn.setShape("diamond");
				}
				vnodes.add(vn);

				
			}
			for(Edge e:edges){
				vEdge ve = new vEdge();
				ve.setFrom(getId.get(e.getSubject()));
				ve.setTo(getId.get(e.getObject()));
				if(e.getObject().startsWith("A")){
					ve.setLabel("AuthoredBy"+"\n"+e.getPredicate());
				}else{
					ve.setLabel("PublishedIn"+"\n"+e.getPredicate());
				}
				vedges.add(ve);
			}
			List<JSONArray> output = new ArrayList<JSONArray>();
			
			output.add(JSONArray.fromObject(vnodes));
			output.add(JSONArray.fromObject(vedges));
			
			return output;
		
		
		
		
	}

}
