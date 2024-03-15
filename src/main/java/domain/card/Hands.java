package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {

    private static final int ADDITIONAL_SCORE = 10;
    private static final int BLACK_JACK_COUNT = 21;
    private static final int MIN_CARD_COUNT = 2;

    private final List<Card> value;

    public Hands() {
        this.value = new ArrayList<>();
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Card card : value) {
            totalScore += card.getScore();
        }

        if (hasAce()) {
            totalScore = calculateAceScore(totalScore);
        }
        return totalScore;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateAceScore(int totalScore) {
        if (totalScore + ADDITIONAL_SCORE <= BLACK_JACK_COUNT) {
            totalScore = totalScore + ADDITIONAL_SCORE;
        }
        return totalScore;
    }

    public boolean isBlackJack() {
        return (calculateScore() == BLACK_JACK_COUNT) && (getCardCount() == MIN_CARD_COUNT);
    }

    public void receive(Card card) {
        value.add(card);
    }

    public int getCardCount() {
        return value.size();
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }
}
