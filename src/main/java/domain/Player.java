package domain;

public class Player extends Participant {

    private static final String BAN_NAME_ERROR_MESSAGE = "Player 의 이름은 딜러일 수 없습니다.";
    private static final String DEALER_NAME = "딜러";

    public Player(Name name, Cards cards) {
        super(name, cards);
        validate(name);
    }

    private void validate(Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(BAN_NAME_ERROR_MESSAGE);
        }
    }

    public int getTotalScoreValue() {
        return cards.getScore(BUST_LIMIT)
                .getValue();
    }

    public boolean isMoreCardAble() {
        return cards.getScore(BUST_LIMIT)
                .isPlayerMoreCardAble();
    }

}
