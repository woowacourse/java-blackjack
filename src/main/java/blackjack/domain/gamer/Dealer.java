package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;

public class Dealer extends Player {
    public static final int HIT_UPPER_BOUND = 16;
    private static final int FIRST_DEAL_CARD_INDEX = 0;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public boolean isScoreUnderBound() {
        return hand.calculateScore() <= HIT_UPPER_BOUND;
    }

    @Override
    public List<Card> getCards() {
        if (hand.getMyCards().size() == 2) {
            return List.of(hand.getMyCardAt(FIRST_DEAL_CARD_INDEX));
        }
        return hand.getMyCards();
    }
}
