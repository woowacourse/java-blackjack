package domain.area;

import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardArea {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score calculate() {
        Score score = score();

        if (hasAce()) {
            score = score.plusTenIfNotBurst();
        }

        return score;
    }

    private Score score() {
        return cards.stream()
                    .map(card -> new Score(card.cardValue().value()))
                    .reduce(Score.MIN, (Score::plus));

    }

    private boolean hasAce() {
        return cards.stream()
                    .anyMatch(card -> card.cardValue().isAce());
    }

    public boolean canMoreCard() {
        return calculate().canMoreCard();
    }

    public boolean isBust() {
        return calculate().isBust();
    }

    public Card firstCard() {
        return cards.get(0);
    }
}
