package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;

public class Dealer2 {

    private static final int REVEAL_COUNT = 1;

    private final Hand hand;

    public Dealer2(Hand hand) {
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public Hand revealHand() {
        return hand.revealHand(REVEAL_COUNT);
    }

    public void draw(CardDeck cardDeck) {
        if (canHit()) {
            hand.append(cardDeck.popCard());
        }
    }

    public boolean canHit() {
        return hand.calculateHandScore().isDealerHit();
    }

    public Hand getHand() {
        return hand;
    }
}
