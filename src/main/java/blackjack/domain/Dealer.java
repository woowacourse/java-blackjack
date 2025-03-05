package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardDump cardDump;

    public Dealer(CardDeck cardDeck, CardDump cardDump) {
        this.cardDeck = cardDeck;
        this.cardDump = cardDump;
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
        cardDeck.add(cardDump.drawCard());
    }

    public int calculateTotalCardScore() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        return Collections.max(possibleScore);
    }

    public boolean isBust() {
        int totalScore = calculateTotalCardScore();

        return totalScore > 21;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }
}
