package com.mybank.dao.request;


//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class SignUpRequest {
//    private String firstName;
//    private String lastName;
    private String username;
    private String password;
    private Long employeeId;

	// TODO: Should I add Employee ID here?

    public SignUpRequest() {
		super();
	}
   
    
	public SignUpRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public SignUpRequest(String username, String password, Long employeeId) {
		super();
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
	}


	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
    
    
}