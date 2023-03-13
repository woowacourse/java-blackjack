package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;

public class Hit extends Running {

    Hit(Card card1, Card card2) {
        this(new Hand(card1, card2));
    }

    Hit(Hand hand) {
        super(hand);
    }

    @Override
    public PlayerStatus draw(Card card) {
        hand().add(card);
        if (hand().isBust()) {
            return new Bust(hand());
        }
        return new Hit(hand());
    }
}
