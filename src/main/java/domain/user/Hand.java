package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import domain.game.Score;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MIN_ACE = 1;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        Score score = score();
        if (hasAce()) {
            score = score.minusTenIfNotBust();
        }

        return score.value();
    }

    private Score score() {
        return cards.stream()
                .map(Card::score)
                .reduce(Score.min(), Score::sum);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.getDenomination() == Denomination.ACE)
                .count();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBust() {
        return new Score(calculateScore()).isOverMax();
    }
}
