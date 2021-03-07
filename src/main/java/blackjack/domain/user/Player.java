package blackjack.domain.user;

public class Player extends User {

    private static final int BLACKJACK_UPPER_BOUND = 21;
    private static final String INVALID_PLAYER_NAME_LENGTH_ERROR_MESSAGE = "이름은 1자 이상이어야 합니다.";

    public Player(String name) {
        this.name = validateName(name);
    }

    private String validateName(String name) {
        if (name.trim().length() < 1) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_LENGTH_ERROR_MESSAGE);
        }
        return name.trim();
    }

    public boolean isNotBust() {
        return this.cards.getScore() <= BLACKJACK_UPPER_BOUND;
    }
}
