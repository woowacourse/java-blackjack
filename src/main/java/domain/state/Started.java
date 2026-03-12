package domain.state;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.finished.BlackJack;
import domain.state.running.Hit;
import java.util.List;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    public static State getStartState(Hand hand) {
        if (BlackJack.isBlackJack(hand)) {
            return new BlackJack(hand);
        }
        return new Hit(hand);
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
