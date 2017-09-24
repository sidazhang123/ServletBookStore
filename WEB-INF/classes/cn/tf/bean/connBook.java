package cn.tf.bean;

import java.util.ArrayList;

public class connBook {
	private ArrayList<String> list;
	
	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public connBook(){
		this.list= new ArrayList<String>();
	}
	public void add(String subject){
		this.list.add(subject);
	}
	public ArrayList<String> getList() {
		return list;
	}
	public int getSize(){
		return this.list.size();
	}
	public String get(int index){
		return this.list.get(index);
	}
	


}
