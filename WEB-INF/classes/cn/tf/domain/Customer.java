package cn.tf.domain;

public class Customer {
	
	private String id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private String email;
	private String lastname,firstname,nickname;
	private String creditcardno,yearofbirth;
	private boolean isbanned;
	private String code;  //激活码
	private boolean actived;  //账户是否激活
	//HERE
	public boolean getIsbanned() {
		return isbanned;
	}
	public void setIsbanned(boolean isbanned) {
		this.isbanned = isbanned;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getCreditcardno() {
		return creditcardno;
	}
	public void setCreditcardno(String creditcardno) {
		this.creditcardno = creditcardno;
	}
	public String getYearofbirth() {
		return yearofbirth;
	}
	public void setYearofbirth(String yearofbirth) {
		this.yearofbirth = yearofbirth;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	
	
	

}
