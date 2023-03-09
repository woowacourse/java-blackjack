package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Person {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BURST_SCORE = 21;

    protected List<Card> cards;

    Person() {
        this.cards = new ArrayList<>();
    }

    void addCard(Card card) {
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

    abstract boolean isHit();

    int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    List<Card> getAllCards() {
        return Collections.unmodifiableList(cards);
    }
}
