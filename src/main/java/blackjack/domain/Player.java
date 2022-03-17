package blackjack.domain;

import blackjack.utils.Validator;

public class Player extends Human {

    private static final String EQUALS_DEALER_NAME_MESSAGE = "딜러와 동일한 이름은 사용할 수 없습니다.";
    private static final String DEALER_NAME = "딜러";

    public Player(final String name) {
        super(name);
        Validator.validateNullOrEmpty(name);
        validateEqualsDealerName(name);
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(EQUALS_DEALER_NAME_MESSAGE);
        }
    }

    public boolean isWinner(Dealer dealer) {
        if (getTotal() == dealer.getTotal()) {
            return isBlackjack();
        }
        return getTotal() > dealer.getTotal();
    }

    @Override
    public boolean canDraw() {
        return cards.isUnderBlackjack();
    }
}
