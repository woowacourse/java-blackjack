package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;

public class FixtureCards {
    public static final Card TWO_DIAMONDS = new Card(Number.TWO, Suit.DIAMONDS);
    public static final Card JACK_SPADES = new Card(Number.JACK, Suit.SPADES);
    public static final Card THREE_HEARTS = new Card(Number.THREE, Suit.HEARTS);
    public static final Card ACE_CLUBS = new Card(Number.ACE, Suit.CLUBS);
}
