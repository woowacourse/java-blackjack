package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.deck.HandDeck;
import java.util.List;

public abstract class Gamer {

    protected final HandDeck handDeck = new HandDeck();

    public abstract boolean canHit();

    public final void receiveCard(Card card) {
        handDeck.add(card);
    }

    public final int deckSize() {
        return handDeck.deckSize();
    }

    public final List<Card> allCards() {
        return handDeck.cards();
    }

    public final int totalScore() {
        return handDeck.score();
    }
}
