package domain;

public class User {

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User from(String input) {
        return new User(input);
    }

    public String getName() {
        return name;
    }
}
