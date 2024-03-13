package blackjack.domain.card.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Busted implements HandStatus {

    private final Hand hand;

    public Busted(Hand hand) {
        this.hand = hand;
    }

    @Override
    public HandStatus add(Card card) {
        return null;
    }

    @Override
    public SingleMatchResult matchAtPlayer(HandStatus dealerHand) {
        return null;
    }

    @Override
    public SingleMatchResult matchAtDealer(HandStatus playerHand) {
        return null;
    }
}
