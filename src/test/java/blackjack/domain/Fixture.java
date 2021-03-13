package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Fixture {
    public static Card king = new Card(Suit.CLUB, Denomination.KING);
    public static Card queen = new Card(Suit.CLUB, Denomination.QUEEN);
    public static Card jack = new Card(Suit.CLUB, Denomination.JACK);
    public static Card ace = new Card(Suit.CLUB, Denomination.ACE);
    public static Card two = new Card(Suit.CLUB, Denomination.TWO);
    public static Card three = new Card(Suit.CLUB, Denomination.THREE);
    public static Card four = new Card(Suit.CLUB, Denomination.FOUR);
    public static Card five = new Card(Suit.CLUB, Denomination.FIVE);
    public static Card six = new Card(Suit.CLUB, Denomination.SIX);
    public static Card seven = new Card(Suit.CLUB, Denomination.SEVEN);
    public static Card eight = new Card(Suit.CLUB, Denomination.EIGHT);
    public static Card nine = new Card(Suit.CLUB, Denomination.NINE);
    public static Card ten = new Card(Suit.CLUB, Denomination.TEN);
}
