package blackjack.model.deck;

import blackjack.model.card.Card;
import blackjack.model.gameRule.GameRule;
import blackjack.model.gamer.Score;
import java.util.List;

public class HandDeck {

    private final Deck deck = new Deck();
    private Score score;

    public void add(Card card) {
        deck.add(card);
        calculateDeckScore();
    }

    public int deckSize() {
        return deck.size();
    }

    public List<Card> cards() {
        return deck.cards();
    }

    public int score() {
        return score.getScore();
    }

    private void calculateDeckScore() {
        GameRule.applyCardScoringRule(deck);
        score = new Score(deck.calculateCardScore());
    }
}
