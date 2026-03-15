package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MAX_NON_BUST_SCORE = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_NUMBER_OF_CARDS = 2;
    private static final int ACE_BONUS_SCORE = 10;


    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_NUMBER_OF_CARDS && calculateScore() == BLACKJACK_SCORE;
    }

    public List<String> showHand() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public int calculateScore() {
        int totalScore = sumDefaultScore();

        if (hasAceCard()) {
            return calculateAceBonus(totalScore);
        }

        return totalScore;
    }

    public boolean isBust() {
        return calculateScore() > MAX_NON_BUST_SCORE;
    }

    private int sumDefaultScore() {
        return cards.stream().mapToInt(Card::getScore).sum();
    }

    private int calculateAceBonus(int totalScore) {
        if (totalScore + ACE_BONUS_SCORE <= MAX_NON_BUST_SCORE) {
            return totalScore + ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean hasAceCard() {
        return cards.stream().anyMatch(Card::isAceCard);
    }
}
