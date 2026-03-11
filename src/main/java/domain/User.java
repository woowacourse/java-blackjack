package domain;

public class User extends Player {

    private final String name;
    private int betAmount;

    private User(String name) {
        this.name = name;
    }

    private User(String name, int betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public static User from(String input) {
        String name = input.strip();
        validateName(name);
        return new User(input);
    }

    public static User from(String input, int betAmount) {
        String name = input.strip();
        validateName(name);
        validateBetAmount(betAmount);
        return new User(input, betAmount);
    }

    private static void validateBetAmount(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0보다 커야 합니다.");
        }
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이 아니어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }

}
