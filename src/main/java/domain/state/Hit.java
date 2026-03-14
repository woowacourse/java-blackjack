package domain.state;

import domain.card.Card;
import domain.member.Hand;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        Hand newHand = hand.appendCard(card);

        if (newHand.calculateTotalValue() > BUST_CONDITION) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}
