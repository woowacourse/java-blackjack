package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Hand {
    private final List<Card> cards;
    private Score score;

    public Hand(List<Card> cards, Score score) {
        this.cards = cards;
        this.score = score;
    }

    public Hand() {
        this(new ArrayList<>(), new Score());
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
        this.score = Score.addUpPointToScore(cards);
    }

    public boolean isWinTo(Hand other) {
        return this.score.isGreaterThan(other.score);
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
