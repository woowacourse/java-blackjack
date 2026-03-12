package domain;

public class User extends Player {

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User from(String input) {
        String name = input.strip();
        validateName(name);
        return new User(input);
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이 아니어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

}
