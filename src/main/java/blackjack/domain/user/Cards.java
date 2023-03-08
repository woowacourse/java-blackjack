package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;
    private final Score score;

    public Cards() {
        this.cards = new ArrayList<>();
        this.score = new Score();
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
