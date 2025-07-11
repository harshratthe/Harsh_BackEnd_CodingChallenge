package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
   
	 @Id
	    private String email;

	    private String password;

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(String email, String password) {
			super();
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
			return "User [email=" + email + ", password=" + password + "]";
		}
	
	
	
}
