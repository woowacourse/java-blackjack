package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.card.Score;
import blackjack.model.deck.HandDeck;
import blackjack.model.gameRule.GameRule;
import java.util.List;

public abstract class Gamer {

    private final HandDeck handDeck = new HandDeck();

    public abstract boolean canHit();

    public final void receiveCard(Card card) {
        handDeck.add(card);
    }

    public final Score calculateScore() {
        cardScoring();
        return new Score(handDeck.calculateCardScore());
    }

    public final int deckSize() {
        return handDeck.size();
    }

    private void cardScoring() {
        int aceCount = handDeck.countElevenAce();

        while (aceCount > 0 && handDeck.calculateCardScore() >= GameRule.BUST_STANDARD_SCORE) {
            handDeck.switchAceValueInRow();
            aceCount--;
        }
    }

    public final List<Card> getCards() {
        return handDeck.cards();
    }
}
