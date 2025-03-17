package blackjack.gamer;

public class Nickname {
    private final String name;

    public Nickname(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
