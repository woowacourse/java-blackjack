package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;

public class States {
    static final State BLACKJACK_STATE = new Blackjack(Hand.of(List.of(
            Card.of(Suit.SPADE, Rank.K),
            Card.of(Suit.SPADE, Rank.ACE))));

    static final State BUST_STATE = new Bust(Hand.of(List.of(
            Card.of(Suit.HEART, Rank.Q),
            Card.of(Suit.HEART, Rank.ACE),
            Card.of(Suit.HEART, Rank.K),
            Card.of(Suit.HEART, Rank.J))));
    static final State STAY_8_STATE = new Stay(Hand.of(List.of(
            Card.of(Suit.HEART, Rank.TWO),
            Card.of(Suit.HEART, Rank.SIX))));

    static final State STAY_12_STATE = new Stay(Hand.of(List.of(
            Card.of(Suit.DIAMOND, Rank.TWO),
            Card.of(Suit.DIAMOND, Rank.TEN))));
    static final State STAY_21_STATE = new Stay(Hand.of(List.of(
            Card.of(Suit.HEART, Rank.Q),
            Card.of(Suit.HEART, Rank.SIX),
            Card.of(Suit.HEART, Rank.FIVE))));

}
