package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Gamer {

    protected final Hand hand;
    protected final Profit profit;

    public Gamer() {
        this.hand = new Hand();
        this.profit = Profit.initProfit();
    }

    abstract boolean canReceiveCard();

    abstract String getProfit();

    public boolean isBust() {
        return hand.calculateScore() > Hand.BLACKJACK_BOUND;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public long getScore() {
        return hand.calculateScore();
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }
}
