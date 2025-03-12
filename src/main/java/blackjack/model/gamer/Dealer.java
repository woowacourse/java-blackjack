package blackjack.model.gamer;

import blackjack.model.card.Hand;

public class Dealer extends Gamer {

    private static final String NICKNAME = "딜러";

    public Dealer(final Hand hand) {
        super(hand);
    }
}
