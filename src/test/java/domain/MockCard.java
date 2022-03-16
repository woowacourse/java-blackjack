package domain;

import domain.card.Card;
import domain.card.Face;
import domain.card.Suit;

public class MockCard {
    public static final Card CLUB_ACE_CARD = new Card(Suit.CLUB, Face.ACE);
    public static final Card HEART_TEN_CARD = new Card(Suit.HEART, Face.TEN);
    public static final Card SPADE_NINE_CARD = new Card(Suit.SPADE, Face.NINE);

    private MockCard() {
    }
}
