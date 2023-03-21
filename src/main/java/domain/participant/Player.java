package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    private static final int MAX_NAME_LENGTH = 5;
    protected static final String OVER_MAX_NAME_MSG = "이름은 5자 이하여야 합니다.";
    protected static final String CANNOT_SAME_DEALER_MSG = "딜러와 같은 이름을 사용할 수 없습니다.";

    public Player(String name) {
        super(name);
        validateName(name);
    }

    private void validateName(String name) {
        validateNameLength(name);
        validateSameDealerName(name);
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(OVER_MAX_NAME_MSG);
        }
    }

    private void validateSameDealerName(String name) {
        if (Dealer.NAME.equals(name)) {
            throw new IllegalArgumentException(CANNOT_SAME_DEALER_MSG);
        }
    }

    @Override
    public boolean canAddCard() {
        validateExistenceCards();
        return cards.getScore() < Cards.BLACKJACK_NUMBER;
    }
}
