package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDump;
import java.util.Collections;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    public Dealer(CardDeck cardDeck) {
        super(cardDeck);
    }

    @Override
    public boolean canHit() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        int hitScore = Collections.max(possibleScore);
        return hitScore <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
