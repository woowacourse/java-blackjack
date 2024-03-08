package blackjack.model.gamer;

import blackjack.model.GameRule;
import blackjack.model.card.Card;
import blackjack.model.deck.HandDeck;
import java.util.List;

public abstract class Gamer {

    protected final HandDeck handDeck = new HandDeck();

    public void receiveCard(Card card) {
        handDeck.addCard(card);
        GameRule.adjustAceValue(handDeck);
    }

    public abstract boolean canHit();

    public int calculateTotalScore() {
        return handDeck.calculateTotalScore();
    }

    public List<Card> getHandDeck() {
        return handDeck.getCards();
    }
}
