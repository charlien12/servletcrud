package negi.chintan.app.usermanagement.models;

public class Users {
	private int user_id;
	private String name;
	private String email;
	private String country;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", name=" + name + ", email=" + email + ", country=" + country + "]";
	}
	public Users(int user_id, String name, String email, String country) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String name, String email, String country) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
	}
}
