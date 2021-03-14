package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Blackjack extends Started {
    public static final double PROFIT_RATE = 1.5;

    public Blackjack(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }

    @Override
    public double profit(int betMoney) {
        return betMoney * PROFIT_RATE;
    }
}
