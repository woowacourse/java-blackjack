package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ALTERNATIVE_ACE_SCORE = 1;
    private static final int ALTER_ACE_GAP = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public void saveCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScoreWhileDraw() {
        int sum = 0;
        for (Card card : cards) {
            sum += getScoreToAdd(card);
        }
        return sum;
    }

    private static int getScoreToAdd(final Card card) {
        if (card.isAceCard()) {
            return ALTERNATIVE_ACE_SCORE;
        }
        return card.getScore();
    }

    public int calculateScore(final int blackJackScore) {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCardCount = 0;
        for (Card card : cards) {
            increaseCountIfAceCard(card, aceCardCount);
        }
        while (aceCardCount > 0 && sum > blackJackScore) {
            aceCardCount--;
            sum -= ALTER_ACE_GAP;
        }
        return sum;
    }

    private void increaseCountIfAceCard(final Card card, int aceCardCount) {
        if (card.isAceCard()) {
            aceCardCount++;
        }
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isNotSameAs(final Hand hand) {
        return getTotalSize
    }
}
