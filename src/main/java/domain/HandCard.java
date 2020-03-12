package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Drawable.ACE_BONUS_SCORE;
import static domain.Drawable.BLACKJACK_FULL_SCORE;

public class HandCard {
    private final List<Card> cards;

    public HandCard() {
        cards = new ArrayList<>();
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
        if (sum <= (BLACKJACK_FULL_SCORE - ACE_BONUS_SCORE) && hasAce()) {
            return sum + ACE_BONUS_SCORE;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::isAce)
                .findAny()
                .orElse(false);
    }

    public boolean isOver() {
        return BLACKJACK_FULL_SCORE < getScore();
    }
}
