package domain.user;

public class UserInformation {
    private final String name;
    private boolean isBurst;

    public UserInformation(String name) {
        this.name = name;
        this.isBurst = false;
    }

    public String getName() {
        return name;
    }

    public boolean isBurst() {
        return isBurst;
    }

    public void burst() {
        this.isBurst = true;
    }
}
