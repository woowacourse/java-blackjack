package domain;

import java.util.ArrayList;
import java.util.List;

public class HandFixture {
    public static Hand createHand(List<Card> cards) {
        return new Hand(new ArrayList<>(cards));
    }

    public static Hand createBlackjack() {
        return new Hand(new ArrayList<>(List.of(
                Card.of(CardNumber.J, CardShape.CLOVER),
                Card.of(CardNumber.ACE, CardShape.DIAMOND))));
    }

    public static Hand createBlackjackScore() {
        return new Hand(new ArrayList<>(List.of(
                Card.of(CardNumber.EIGHT, CardShape.CLOVER),
                Card.of(CardNumber.THREE, CardShape.CLOVER),
                Card.of(CardNumber.J, CardShape.DIAMOND))));
    }

    public static Hand createDefault() {
        return new Hand(new ArrayList<>(List.of(
                Card.of(CardNumber.THREE, CardShape.CLOVER),
                Card.of(CardNumber.J, CardShape.DIAMOND))));
    }

    public static Hand createBust() {
        return new Hand(new ArrayList<>(List.of(
                Card.of(CardNumber.J, CardShape.CLOVER),
                Card.of(CardNumber.Q, CardShape.CLOVER),
                Card.of(CardNumber.K, CardShape.DIAMOND))));
    }
}
