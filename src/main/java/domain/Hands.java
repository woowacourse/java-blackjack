package domain;

import java.util.ArrayList;
import java.util.List;

public class Hands {

    private static final int ADDITIONAL_SCORE = 10;
    private static final int BLACK_JACK_COUNT = 21;

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

    private int calculateAceScore(int totalScore) {
        if (totalScore + ADDITIONAL_SCORE <= BLACK_JACK_COUNT) {
            totalScore = totalScore + ADDITIONAL_SCORE;
        }
        return totalScore;
    }

    public void receive(Card card) {
        value.add(card);
    }

    public boolean hasAce() {
        return value.stream()
                .anyMatch(card -> Rank.ACE == card.getRank());
    }

    public int getCardCount() {
        return value.size();
    }

    public List<Card> getValue() {
        return value;
    }
}
