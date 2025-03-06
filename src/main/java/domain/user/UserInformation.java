package domain.user;

public class UserInformation {
    private final String name;
    private UserStatus status;

    public UserInformation(String name) {
        this.name = name;
        this.status = UserStatus.NORMAL;
    }

    public String getName() {
        return name;
    }

    public void burst() {
        this.status = UserStatus.BURST;
    }
}
