package domain;

public class Player extends Participant {

    private static final String BAN_NAME = "딜러";
    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";

    public Player(Name name, Cards cards) {
        super(name, cards);
        validate(name);
    }

    private void validate(Name name) {
        if (name.getValue().equals(BAN_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public int getTotalScore() {
        return cards.calculateScore(BUST_LIMIT);
    }

    public boolean isMoreCardAble() {
        return getTotalScore() < BUST_LIMIT;
    }

}
