package domain;

import java.util.Optional;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck participantDeck) {
        super(participantDeck, DEALER_NAME);
    }

    public Optional<Card> addCardWhenSumBelowMinimum(Deck totalDeck) {
        if (super.calculateDeckSum() <= MINIMUM_TOTAL_SCORE) {
            Card newCard = totalDeck.drawCard();
            super.addCard(newCard);
            return Optional.of(newCard);
        }
        return Optional.empty();
    }
}
