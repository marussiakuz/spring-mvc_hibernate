package web.model;

public class UserInDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long birthday;
    private Role role;

    public UserInDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Role getRole() {
        return role;
    }
}
