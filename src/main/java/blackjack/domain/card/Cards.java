package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int READY_SIZE = 2;

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public Cards() {
        this(Collections.emptyList());
    }

    public int totalScore() {
        ArrayList<Card> newCards = new ArrayList<>(value);
        newCards.sort(Comparator.naturalOrder());

        int totalScore = 0;
        for (Card card : newCards) {
            totalScore = card.calculateScore(totalScore);
        }

        return totalScore;
    }

    public boolean isBlackjack() {
        return isReady() && hasAce() && totalScore() == BLACKJACK_SCORE;
    }

    public boolean isReady() {
        return value.size() == READY_SIZE;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return totalScore() > BLACKJACK_SCORE;
    }

    public void append(Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }
}
