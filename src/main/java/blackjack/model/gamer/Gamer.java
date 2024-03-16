package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.deck.Deck;
import blackjack.model.gameRule.GameRule;
import java.util.List;

public abstract class Gamer {

    private final Deck deck = new Deck();

    public abstract boolean canHit();

    public final void receiveCard(Card card) {
        deck.add(card);
    }

    public final Score calculateScore() {
        cardScoring();
        return new Score(deck.calculateCardScore());
    }

    public final int deckSize() {
        return deck.size();
    }

    private void cardScoring() {
        int aceCount = deck.countElevenAce();

        while (aceCount > 0 && deck.calculateCardScore() >= GameRule.BUST_STANDARD_SCORE) {
            deck.switchAceValueInRow();
            aceCount--;
        }
    }

    public final List<Card> getCards() {
        return deck.cards();
    }
}
