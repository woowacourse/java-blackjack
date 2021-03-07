package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public abstract class Participant {
    private final String name;
    protected final Hand hand;

    public Participant(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveInitialCard(final CardDeck cardDeck) {
        hand.add(cardDeck.distribute());
        hand.add(cardDeck.distribute());
    }

    public void receiveAdditionalCard(final Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
