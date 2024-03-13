package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public abstract class Participant {

    private final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
    }

    public abstract boolean canHit();

    public void draw(Card card) {
        if (canHit()) {
            hand.append(card);
        }
    }

    public Score getHandScore() {
        return hand.calculateHandScore();
    }

    public Hand getHand() {
        return hand;
    }
}
