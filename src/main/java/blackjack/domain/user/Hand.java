package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final Score score;

    public Hand() {
        this.cards = new ArrayList<>();
        this.score = new Score();
    }

    Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.score = new Score();
        score.calculateScore(this);
    }

    public void add(Card card) {
        cards.add(card);
        score.calculateScore(this);
    }

    public boolean isBust() {
        return score.isBust();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score getScore() {
        return score;
    }
}
