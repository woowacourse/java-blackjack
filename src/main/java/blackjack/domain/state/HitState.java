package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class HitState extends State {

    HitState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Deck deck) {
        Card card = deck.draw();
        Hand newHand = getHand().add(card);
        if (newHand.isBust()) {
            return new BustState(newHand);
        }
        return new HitState(newHand);
    }

    @Override
    public State stand() {
        return new StandState(getHand());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double getProfitRate(Hand other) {
        throw new UnsupportedOperationException("현재 상태에서는 할 수 없습니다.");
    }
}
