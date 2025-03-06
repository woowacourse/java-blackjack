package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    public Dealer(CardDeck cardDeck, CardDump cardDump) {
        super(cardDeck, cardDump);
    }

    public boolean didHit() {
        if (canHit()) {
            addCard();
            return true;
        }
        return false;
    }

    @Override
    boolean canHit() {
        int score = calculateTotalCardScore();
        return score <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        return Collections.max(possibleScore);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
