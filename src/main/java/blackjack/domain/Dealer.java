package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardGenerator cardGenerator;

    public Dealer(CardDeck cardDeck, CardGenerator cardGenerator) {
        this.cardDeck = cardDeck;
        this.cardGenerator = cardGenerator;
    }

    public boolean hasTakenExtraCard() {
        if (mustTakeExtraCard()) {
            takeExtraCard();
            return true;
        }
        return false;
    }

    private boolean mustTakeExtraCard() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        int max = Collections.max(possibleScore);

        return max <= 16;
    }

    private void takeExtraCard() {
        cardDeck.add(cardGenerator.generate());
    }
}
