package blackjack.domain;

public class Player {

    private final String name;

    public Player(String name) {
        validateNameLength(name);
        this.name = name;

    }

    private void validateNameLength(final String name) {
        if (name.length() < 1 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
        }
    }
}
