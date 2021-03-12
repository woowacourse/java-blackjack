package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Fixture {
    public static Card jack = new Card(Suit.CLUB, Denomination.JACK);
    public static Card ace = new Card(Suit.CLUB, Denomination.ACE);
    public static Card seven = new Card(Suit.CLUB, Denomination.SEVEN);
    public static Card six = new Card(Suit.CLUB, Denomination.SIX);
}
