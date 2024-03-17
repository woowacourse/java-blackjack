package blackjack.model.fixture;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import blackjack.model.participant.Hand;
import java.util.List;

public enum HandFixture {
    BLACKJACK_HAND(new Hand(List.of(Card.from(Shape.DIA, Score.TEN), Card.from(Shape.DIA, Score.ACE)))),
    BUST_HAND(new Hand(List.of(Card.from(Shape.DIA, Score.TEN), Card.from(Shape.DIA, Score.QUEEN),
            Card.from(Shape.DIA, Score.JACK)))),
    NOT_BLACKJACK_BUT_21_HAND(new Hand(List.of(Card.from(Shape.DIA, Score.TEN), Card.from(Shape.DIA, Score.SIX),
            Card.from(Shape.DIA, Score.FIVE)))),
    UNDER_16_HAND(new Hand(List.of(Card.from(Shape.DIA, Score.TEN), Card.from(Shape.DIA, Score.TWO)))),
    OVER_16_UNDER_21_HAND(new Hand(List.of(Card.from(Shape.DIA, Score.TEN), Card.from(Shape.DIA, Score.EIGHT))));

    private final Hand hand;

    HandFixture(final Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}
