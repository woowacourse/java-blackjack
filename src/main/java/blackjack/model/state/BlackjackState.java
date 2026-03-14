package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import java.util.List;

public abstract class BlackjackState {

    protected final Hand hand;

    public BlackjackState(Hand hand) {
        this.hand = hand;
    }

    public static BlackjackState init() {
        return new Hit(new Hand());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public abstract BlackjackState hit(Card newCard);

    public abstract boolean canHit();

    public abstract double getEarningRate();
}
