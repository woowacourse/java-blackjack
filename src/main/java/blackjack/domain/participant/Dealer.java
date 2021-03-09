package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    public static final int DEALER_SCORE_PIVOT = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(final Cards cards) {
        super(cards, DEALER_NAME);
    }

    public boolean doneReceiving() {
        return getScore() > DEALER_SCORE_PIVOT;
    }

    @Override
    public List<Card> getInitCardsAsList() {
        return new ArrayList<>(Collections.singletonList(hand.getFirstCard()));
    }
}
