package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Hit extends Started {
    public static final int PROFIT_RATE = 1;

    public Hit(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public double profit(int betMoney) {
        return betMoney * PROFIT_RATE;
    }
}
