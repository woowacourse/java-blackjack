package blackjack.model.deck;

import blackjack.model.GameRule;
import blackjack.model.gamer.Score;
import blackjack.model.card.Card;
import java.util.List;

public class HandDeck {

    private final Deck deck = new Deck();
    private Score score;

    public void add(Card card) {
        deck.add(card);
        calculateDeckScore();
    }

    public List<Card> cards() {
        return deck.cards();
    }

    public int score() {
        return score.getScore();
    }

    private void calculateDeckScore() {
        GameRule.applyCardScoringRules(deck);
        score = new Score(deck.calculateCardScore());
    }
}
