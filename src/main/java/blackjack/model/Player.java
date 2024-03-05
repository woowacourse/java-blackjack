package blackjack.model;

public class Player {
    private static final String INVALID_NAME_LENGTH = "참여자 이름은 한 글자 이상이다";

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }
}
