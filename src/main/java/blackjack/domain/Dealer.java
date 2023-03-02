package blackjack.domain;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public List<Card> getInitialStatus() {
        return List.of(getStatus().get(0));
    }
}
