package tech.codingclub.helix.entity;

public class LoginResponse {

    public Long id;
    public boolean is_logined;
    public String message;

    public LoginResponse(Long id, boolean is_logined, String message) {
        this.id = id;
        this.is_logined = is_logined;
        this.message = message;
    }

    public LoginResponse() {
    }

}
