package domain;

import java.util.Optional;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck participantDeck) {
        super(participantDeck, DEALER_NAME);
    }

    @Override
    public Optional<Card> addCard(Deck totalDeck) {
        if (super.calculateDeckSum() <= MINIMUM_TOTAL_SCORE) {
            return super.addCard(totalDeck);
        }
        return Optional.empty();
    }
}
