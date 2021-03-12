package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Blackjack implements State {
    private final Hand hand;

    public Blackjack(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public int softHandSum() {
        return hand.softSum();
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }
}
