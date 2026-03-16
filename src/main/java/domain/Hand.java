package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

import static domain.card.Rank.ACE;

public class Hand {
    public static final int ACE_SCORE_ADJUSTMENT = 10;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public int getSum() {
        int sum = getInitSum();
        long aceCount = countAce();

        while (aceCount > 0 && sum > BlackjackRule.BLACKJACK_SCORE) {
            sum -= ACE_SCORE_ADJUSTMENT;
            aceCount--;
        }

        return sum;
    }

    private int getInitSum() {
        return cards.stream()
                .mapToInt(c -> c.rank().getScore())
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(c -> c.rank() == ACE)
                .count();
    }

    public boolean isBurst() {
        return getSum() > BlackjackRule.BLACKJACK_SCORE;
    }

    public boolean isLessThanBlackJack() {
        return getSum() < BlackjackRule.BLACKJACK_SCORE;
    }

    public int getSize() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
