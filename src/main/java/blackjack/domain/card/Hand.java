package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ACE_BONUS = 10;
    private static final int MAXIMUM_UPDATABLE_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand addCard(final Card card) {
        final List<Card> originHand = new ArrayList<>(cards);
        originHand.add(card);
        return new Hand(originHand);
    }

    public int calculateTotalScore() {
        final int score = getTotalScore();
        if (isExistAce() && isScoreUpdatable(score)) {
            return score + ACE_BONUS;
        }
        return score;
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isExistAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isScoreUpdatable(final int score) {
        return score + ACE_BONUS <= MAXIMUM_UPDATABLE_SCORE;
    }

    public int count() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
