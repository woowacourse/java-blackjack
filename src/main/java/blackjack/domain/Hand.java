package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BURST_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    void add(Card card) {
        cards.add(card);
    }

    boolean hasACE() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    int calculateScore() {
        int totalScore = getTotalScore();
        if (totalScore > BURST_SCORE && hasACE()) {
            return totalScore - ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    List<Card> getAllCards() {
        return cards;
    }

    Card getFirstCard() {
        return cards.get(0);
    }
}
