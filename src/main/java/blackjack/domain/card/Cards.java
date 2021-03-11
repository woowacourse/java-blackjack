package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards implements Comparable<Cards> {
    private static final int FIRST_CARD = 0;
    private static final int BUST = 21;
    private static final int INITIAL_CARD_SIZE = 2;
    private static final int BLACKJACK = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card oneCard() {
        return this.cards.get(FIRST_CARD);
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.getCards());
    }

    public boolean isBust() {
        return calculateScore() > BUST;
    }

    public boolean isBlackjack() {
        return this.cards.size() == INITIAL_CARD_SIZE && calculateScore() == BLACKJACK;
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        if (this.containAce() && score > BUST) {
            score -= 10;
        }
        return score;
    }

    public boolean containAce() {
        return this.cards.stream()
                .anyMatch(Card::hasAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    @Override
    public int compareTo(Cards otherCards) {
        return Integer.compare(this.calculateScore(), otherCards.calculateScore());
    }
}
