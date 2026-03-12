package blackjack.domain.hand;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int ACE_MAX_CONDITION = 11;
    public static final int ACE_DIFFERENCE = 10;
    public static final int BLACKJACK_CARD_COUNTS = 2;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Score calculateScore() {
        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();
        int score = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        score = translateAce(score, aceCount);
        return new Score(score);
    }

    private int translateAce(int score, long aceCount) {
        while (score <= ACE_MAX_CONDITION && aceCount > 0) {
            score += ACE_DIFFERENCE;
            aceCount--;
        }
        return score;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNTS && calculateScore().isBlackjackNumber();
    }
}
