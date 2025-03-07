package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class Participant {
    static final int TARGET_SCORE = 21;

    protected final CardDeck cardDeck;
    protected final CardDump cardDump;

    public Participant(CardDeck cardDeck, CardDump cardDump) {
        this.cardDeck = cardDeck;
        this.cardDump = cardDump;
    }

    abstract boolean canHit();

    public int calculateScore() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        return possibleSum.stream()
                .filter(sum -> sum <= TARGET_SCORE)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSum));
    }

    public boolean isBust() {
        return calculateScore() > TARGET_SCORE;
    }

    public void addCard() {
        cardDeck.add(cardDump.drawCard());
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }

    public abstract String getName();
}
