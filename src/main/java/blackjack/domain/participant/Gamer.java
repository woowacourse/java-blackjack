package blackjack.domain.participant;

import blackjack.domain.card.Hand;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer(final Hand hand) {
        this.hand = hand;
    }

    public abstract Hand showInitialCards();

    public abstract boolean canGetMoreCard();

    public abstract String getNickname();

    public void receiveCards(final Hand givenHand) {
        hand.addAll(givenHand);
    }

    public int calculateMaxScore() {
        return hand.calculateResult();
    }

    public Hand showAllCards() {
        return hand;
    }
}
