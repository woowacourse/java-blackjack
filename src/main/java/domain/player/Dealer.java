package domain.player;

import domain.card.Card;

import java.util.List;

public class Dealer extends Player {
    private static final String DEALER_NAME = "딜러";
    private static final int STANDARD = 16;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public boolean isAdditionalCard() {
        return sumCardNumber() <= STANDARD;
    }
}
