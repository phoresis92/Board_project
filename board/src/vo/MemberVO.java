package vo;

public class MemberVO {

	private String userid;
	private String password;
	private String name;
	private String address;
	private String gender;
	private String indate;
	private String phone;
	private String email;

	public MemberVO(String userid, String password, String name, String address, String gender, String indate,
			String phone, String email) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.indate = indate;
		this.phone = phone;
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
