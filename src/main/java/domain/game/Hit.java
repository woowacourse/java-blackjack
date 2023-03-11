package domain.game;

import domain.card.Card;
import domain.user.Hand;

import java.util.List;

public class Hit implements State {

    private final Hand hand;

    public Hit(Card card1, Card card2) {
        this(new Hand(List.of(card1, card2)));
    }

    public Hit(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }

        return new Hit(hand);
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }
}
