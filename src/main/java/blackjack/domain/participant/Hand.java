package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int INIT_HAND_COUNT = 2;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score totalScore() {
        Score score = new Score(calculateScore());
        long acesCount = acesCount();
        for (int i = 0; i < acesCount; i++) {
            score = score.changeHardAce();
        }
        return score;
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private long acesCount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBlackjack() {
        return cards.size() == INIT_HAND_COUNT && totalScore().isBlackjack();
    }

    public boolean isBust() {
        return totalScore().isBust();
    }

    public List<Card> toList() {
        return new ArrayList<>(cards);
    }
}
