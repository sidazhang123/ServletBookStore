package cn.tf.bean;

public class vEdge {
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	private int from;
	private int to;
	private String label;
	private String arrows;
	private String color;
	private int width;
	private int length;
	public vEdge(){
		this.arrows = "to";
		this.color = "black";
		this.width = 1;
		this.length = 200;
		
	}
	

}
