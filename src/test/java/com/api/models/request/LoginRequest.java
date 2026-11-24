package com.api.models.request;

/*      POJO/Model
		RequestModel Class
			Add private variables for payload keys taken from actual request
			*If inner JSON then create another POJO for that also
			Rt click >source> Generate constructor using fields
			Rt click >source> Generate getter setter
			Rt click >source> Generate toString()
*/
public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" + "email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }
}
