package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
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

    private boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public List<Card> toList() {
        return new ArrayList<>(cards);
    }
}
