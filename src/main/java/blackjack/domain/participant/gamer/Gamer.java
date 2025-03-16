package blackjack.domain.participant.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer(final Hand hand) {
        this.hand = hand;
    }

    public abstract Hand showInitialCards();

    public abstract boolean canHit();

    public abstract String getNickname();

    public void receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
    }

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateResult();
    }

    public Hand showAllCards() {
        return hand;
    }
}
