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

    public boolean isBust() {
        return cards.getPlayerScore()
                .isBust();
    }

    public boolean isBlackJack() {
        return cards.getPlayerScore()
                .isMaxScore() && cards.getSize() == NUMBER_OF_CARDS_BLACKJACK;
    }

    public int getTotalScoreValue() {
        return cards.getPlayerScore()
                .getValue();
    }

    public boolean isMoreCardAble() {
        return cards.getPlayerScore()
                .isPlayerMoreCardAble();
    }

}
