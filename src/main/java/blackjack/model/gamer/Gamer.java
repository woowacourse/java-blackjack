package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.Deck;
import blackjack.model.GameRule;
import java.util.List;

public abstract class Gamer {

    protected final Deck deck = new Deck();

    public void receiveCard(Card card) {
        deck.addCard(card);
        GameRule.adjustAceValue(deck);
    }

    public abstract boolean canHit();

    public int calculateTotalScore() {
        return deck.calculateTotalScore();
    }

    public List<Card> getDeck() {
        return deck.getCards();
    }
}
