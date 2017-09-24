package cn.tf.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class NoticeList implements Serializable{
	private ArrayList<String> list;
	
	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public NoticeList(){
		this.list= new ArrayList<String>();
	}

	public ArrayList<String> getList() {
		return list;
	}
	public int getSize(){
		return this.list.size();
	}
	
	public void addBookName(String bookname) {
		this.list.add(bookname);
		
	}
	
	public String getBookName(int index){
		return this.list.get(index);
	}
	
	

}
