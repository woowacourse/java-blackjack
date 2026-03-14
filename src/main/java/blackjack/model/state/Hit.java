package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;

public class Hit extends Playing {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public BlackjackState hit(Card newCard) {
        Hand newHand = hand.addCard(newCard);

        if (newHand.isBust()) {
            return new Bust(newHand);
        }
        if (newHand.isBlackjack()) {
            return new Blackjack(newHand);
        }

        return new Hit(newHand);
    }

    @Override
    public double getEarningRate() {
        return 1;
    }
}
