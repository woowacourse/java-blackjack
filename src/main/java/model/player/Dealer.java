package model.player;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Dealer extends Player {

    private static final int NUMBER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() <= NUMBER_THRESHOLD;
    }
}
