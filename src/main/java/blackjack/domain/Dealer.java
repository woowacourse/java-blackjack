package blackjack.domain;

public class Dealer extends User {

    static final String DEALER_NAME = "딜러";

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }
}
