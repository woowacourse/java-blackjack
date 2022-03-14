package domain.participant;

import java.util.List;

import domain.card.Card;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int STANDARD_SCORE_OF_ENOUGH_CARD = 16;

    public Dealer(List<Card> hand) {
        super(new Name(DEALER_NAME), hand);
    }

    @Override
    public boolean isNeedToDraw() {
        return calculateBestScore() <= STANDARD_SCORE_OF_ENOUGH_CARD;
    }

    public Card getFirstHand() {
        return getHand().get(FIRST_INDEX_OF_HAND);
    }
}
