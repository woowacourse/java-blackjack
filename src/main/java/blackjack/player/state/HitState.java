package blackjack.player.state;

import blackjack.card.Card;
import blackjack.player.Hand;

public class HitState extends PlayerState {

    public HitState(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerState drawCard(Card card) {
        Hand newHand = hand.addCard(card);
        if (newHand.isBusted()) {
            return new BustedState(newHand);
        }
        return new HitState(newHand);
    }

    @Override
    public PlayerState stand() {
        return new StandState(hand);
    }
}
