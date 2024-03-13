package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.deck.HandDeck;

import java.util.List;

public abstract class Gamer {

    protected final HandDeck handDeck = new HandDeck();

    public Gamer() {
    }

    public abstract boolean canHit();

    public boolean isBusted() {
        return handDeck.isBusted();
    }

    public boolean isBlackJack() {
        return handDeck.isBlackJack();
    }

    public final void receiveCard(Card card) {
        handDeck.addCard(card);
    }

    public final int calculateTotalScore() {
        return handDeck.calculateTotalScore();
    }

    public final List<Card> getHandDeck() {
        return handDeck.getCards();
    }
}
