package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cards {

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public Cards() {
        this(new ArrayList<>());
    }

    public boolean isBlackjack() {
        return isReady() && hasAce() && totalScore() == 21;
    }

    public boolean isReady() {
        return value.size() == 2;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return totalScore() > 21;
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

    public void append(Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }
}
