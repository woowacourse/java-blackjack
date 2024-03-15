package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

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

    public Score handScore() {
        return hand.calculateHandScore();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isNotBlackjack() {
        return !hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
