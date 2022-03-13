package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_GAP = 10;
    private static final int BLACK_JACK_SCORE = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int oneAceScore = calculateOneAceScore();
        if (hasAce() && oneAceScore + ACE_GAP <= BLACK_JACK_SCORE) {
            return oneAceScore + ACE_GAP;
        }
        return oneAceScore;
    }

    private int calculateOneAceScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private boolean hasAce() {
        return cards.stream().
            anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
