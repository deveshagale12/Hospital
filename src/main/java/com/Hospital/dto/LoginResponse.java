
package com.Hospital.dto;


public class LoginResponse {
    private boolean success;
    private String message;
    private Long id;
    private String fullName;
    private String email;

    // Constructors
    public LoginResponse() {}

    public LoginResponse(boolean success, String message, Long id, String fullName, String email) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}