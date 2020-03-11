package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int ACE_CRITICAL_POINT = 11;
    private static final int ACE_UPPER_POINT = 11;
    private static final int ACE_LOWER_POINT = 1;
    private static final int DEALER_CRITICAL_SCORE = 16;

    private static Dealer dealer_instance;

    private List<Card> cards = new ArrayList<>();

    private Dealer() {
    }

    public static Dealer getDealer() {
        if (dealer_instance == null) {
            dealer_instance = new Dealer();
        }
        return dealer_instance;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce() && score <= ACE_CRITICAL_POINT) {
            score += ACE_UPPER_POINT - ACE_LOWER_POINT;
        }
        return score;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isUnderCriticalScore() {
        return calculateScore() <= DEALER_CRITICAL_SCORE;
    }
}