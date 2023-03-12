package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    private static final int MAX_NAME_LENGTH = 5;

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
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }

    private void validateSameDealerName(String name) {
        if (Dealer.NAME.equals(name)) {
            throw new IllegalArgumentException("딜러와 같은 이름을 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canAddCard() {
        validateExistenceCards();
        return cards.getScore() < Cards.BLACKJACK_NUMBER;
    }
}
