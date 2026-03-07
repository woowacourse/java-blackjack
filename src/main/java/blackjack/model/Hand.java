package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Hand(ScoreCalculator scoreCalculator) {
        this.cards = new ArrayList<>();
        this.scoreCalculator = scoreCalculator;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Score calculateScore() {
        return scoreCalculator.calculate(cards);
    }
}
