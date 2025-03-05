package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";

    public Dealer(CardDeck cardDeck, CardDump cardDump) {
        super(cardDeck, cardDump);
    }

    public boolean hasTakenExtraCard() {
        if (canTakeExtraCard()) {
            addCard();
            return true;
        }
        return false;
    }

    @Override
    boolean canTakeExtraCard() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        int max = Collections.max(possibleScore);

        return max <= 16;
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
