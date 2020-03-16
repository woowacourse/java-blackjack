package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    public static final int ACE_BONUS_SCORE = 10;
    public static final int PERFECT_SCORE = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;

    public HandCard() {
        cards = new ArrayList<>();
    }

    public boolean isNotEmpty() {
        return !(cards.isEmpty());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public String getNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(","));
    }

    public int getScore() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        return getAceScore(sum);
    }

    private int getAceScore(int sum) {
        if (sum <= (PERFECT_SCORE - ACE_BONUS_SCORE) && hasAce()) {
            return sum + ACE_BONUS_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return PERFECT_SCORE < getScore();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && getScore() == PERFECT_SCORE;
    }
}
