package domain.game;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public HandState draw(Card card) {
        Hand newHand = hand().addCard(card);
        if (newHand.isBlackjack()) {
            return new Blackjack(newHand);
        }
        if (newHand.isBust()) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }

    @Override
    public HandState stay() {
        return new Stay(hand());
    }
}
