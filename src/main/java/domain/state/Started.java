package domain.state;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.finished.Blackjack;
import domain.state.running.Hit;
import java.util.List;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    public static State getStartState(Hand hand) {
        if (Blackjack.isBlackJack(hand)) {
            return new Blackjack(hand);
        }
        Hit hit = new Hit(hand);
        if (hit.isFinished()) {
            return hit.stay();
        }
        return hit;
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
