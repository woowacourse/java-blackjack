package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards implements Comparable<Cards> {
    private static final int FIRST_CARD = 0;
    private static final int INITIAL_CARD_SIZE = 2;

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
        return calculateScore().isBust();
    }

    public boolean isBlackjack() {
        return isInitialCardSize() && calculateScore().isBlackjack();
    }

    private boolean isInitialCardSize() {
        return this.cards.size() == INITIAL_CARD_SIZE;
    }

    public Score calculateScore() {
        Score score = new Score(sumScore());
        if (hasAce()) {
            score = score.minusTenIfBust();
        }
        return score;
    }

    private int sumScore() {
        return this.cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean hasAce() {
        return this.cards.stream()
                .anyMatch(Card::hasAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    @Override
    public int compareTo(Cards otherCards) {
        return Integer.compare(this.calculateScore().getScore(), otherCards.calculateScore().getScore());
    }
}
