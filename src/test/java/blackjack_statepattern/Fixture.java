package blackjack_statepattern;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Denomination;
import blackjack_statepattern.card.Suit;

public class Fixture {
    public static final Card SPADES_ACE = Card.of(Suit.SPADES, Denomination.ACE);
    public static final Card SPADES_JACK = Card.of(Suit.SPADES, Denomination.JACK);
    public static final Card SPADES_TWO = Card.of(Suit.SPADES, Denomination.TWO);
    public static final Card SPADES_TEN = Card.of(Suit.SPADES, Denomination.TEN);

}
