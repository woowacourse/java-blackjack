package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class Participant {
    static final int TARGET_SCORE = 21;

    protected final CardDeck cardDeck;

    public Participant(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
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

    public void receiveCard(Card card) {
        cardDeck.add(card);
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }

    public int getCardSize() {
        return cardDeck.getDeckSize();
    }
    public abstract String getName();
}
