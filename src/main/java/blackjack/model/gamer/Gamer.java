package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.deck.HandDeck;
import java.util.List;

public abstract class Gamer {

    protected final HandDeck handDeck = new HandDeck();

    public void receiveCard(Card card) {
        handDeck.add(card);
    }

    public List<Card> allCards() {
        return handDeck.cards();
    }

    public int totalScore() {
        return handDeck.score();
    }

    public abstract boolean canHit();
}
