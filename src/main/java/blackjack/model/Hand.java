package blackjack.model;

import static blackjack.model.BlackjackConfig.ACE_HIGH_SCORE;
import static blackjack.model.BlackjackConfig.ACE_LOW_SCORE;
import static blackjack.model.BlackjackConfig.BLACKJACK_SCORE;
import static blackjack.model.BlackjackConfig.INITIAL_CARD_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {

        int baseScore = cards.stream()
                .mapToInt((card) -> card.getRank().getScore())
                .sum();

        int aceCount = (int) cards.stream()
                .filter((card) -> card.isAce())
                .count();

        for (int i = 0; i < aceCount; i++) {
            baseScore = convertAceToEleven(baseScore);
        }
        return baseScore;

    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE && cards.size() == INITIAL_CARD_COUNT;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    private int convertAceToEleven(int currentScore) {
        if (currentScore + (ACE_HIGH_SCORE - ACE_LOW_SCORE) <= BLACKJACK_SCORE) {
            return currentScore + (ACE_HIGH_SCORE - ACE_LOW_SCORE);
        }
        return currentScore;
    }

}

