package blakjack.domain;

import blakjack.domain.card.Card;
import blakjack.domain.card.Face;
import blakjack.domain.card.Suit;

public class MockCard {
    public final static Card CLUB_TEN = new Card(Suit.CLUB, Face.TEN);
    public final static Card HEART_ACE = new Card(Suit.HEART, Face.ACE);

}
