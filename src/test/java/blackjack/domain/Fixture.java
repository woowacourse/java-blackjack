package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;

public class Fixture {
    public static final Card CLUBS_ACE = new Card(Suit.CLUB, CardNumber.ACE);
    public static final Card CLUBS_TWO = new Card(Suit.CLUB, CardNumber.TWO);
    public static final Card CLUBS_TEN = new Card(Suit.CLUB, CardNumber.TEN);
    public static final Card CLUBS_KING = new Card(Suit.CLUB, CardNumber.KING);
}
