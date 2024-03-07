package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.HandDeck;
import blackjack.model.GameRule;
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

    public List<Card> getDeck() {
        return handDeck.getCards();
    }
}
