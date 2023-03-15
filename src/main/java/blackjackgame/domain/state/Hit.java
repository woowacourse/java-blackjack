package blackjackgame.domain.state;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Hand;

public final class Hit extends Running {

    Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        Hand newHand = add(card);

        if (newHand.isBust()) {
            return new Bust(newHand);
        }

        if (newHand.isBlackJack()) {
            return new BlackJack(newHand);
        }

        return new Hit(newHand);
    }
}
