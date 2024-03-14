package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;

public class HitState extends State {

    public HitState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Deck deck) {
        Card card = deck.draw();
        Hand newHand = hand.add(card);
        if (newHand.isBust()) {
            return new BustState(newHand);
        }
        return new HitState(newHand);
    }

    @Override
    public State stand() {
        return new StandState(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
