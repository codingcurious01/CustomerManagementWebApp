package in.bmart.model;

// Customer Bean class
public class Customer 
{
	private Integer id;
	private String name;
	private Long mobile;
	private String state;
	private String email;
	private String gender;
	
	// Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	// Constructors
	public Customer(Integer id, String name, Long mobile, String state, String email, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.state = state;
		this.email = email;
		this.gender = gender;
	}
	public Customer(String name, Long mobile, String state, String email, String gender) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.state = state;
		this.email = email;
		this.gender = gender;
	}
	public Customer() {
		super();
	}
	
}
