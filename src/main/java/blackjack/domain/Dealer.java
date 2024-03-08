package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;

public class Dealer {

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public void appendCard(Card card) {
        hand.append(card);
    }

    public int calculateHandSum() {
        return hand.sum();
    }

    public Hand getHand() {
        return hand;
    }
}
