package domain.hand;

import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score calculate() {
        Score score = score();

        if (isSoftHand()) {
            return score.plusSoftHand();
        }

        return score;
    }

    private Score score() {
        return cards.stream()
                    .map(card -> new Score(card.cardValue().value()))
                    .reduce(Score.MIN, (Score::plus));

    }

    private boolean isSoftHand() {
        return cards.stream()
                    .anyMatch(card -> card.cardValue().isAce());
    }

    public boolean canMoreCard() {
        return calculate().canMoreCard();
    }

    public boolean isBust() {
        return calculate().isBust();
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculate().isBlackjack();
    }

    public Card firstCard() {
        return cards.get(0);
    }

    public Card secondCard() {
        return cards.get(1);
    }
}
