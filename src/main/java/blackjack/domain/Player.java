package blackjack.domain;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player create(String name) {
        validateSpace(name);
        return new Player(name);
    }

    private static void validateSpace(String name) {
        if(name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백이 포함됩니다.");
        }
    }
}
