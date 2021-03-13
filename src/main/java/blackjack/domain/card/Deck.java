package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    private Deck(Card... cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public static Deck of(Card... cards) {
        return new Deck(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score score() {
        Score score = Score.of(sumOfScore());
        if (containsAce()) {
            score = score.addAceNumber();
        }
        return score;
    }

    private int sumOfScore() {
        return cards.stream()
            .mapToInt(Card::score)
            .sum();
    }

    private boolean containsAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        return score().isBlackJack();
    }

    public boolean isBust() {
        return score().isBust();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}
