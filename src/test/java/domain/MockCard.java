package domain;

import domain.card.Card;
import domain.card.Face;
import domain.card.Suit;

public class MockCard {
    static final Card ACE_CARD = new Card(Suit.CLUB, Face.ACE);
    static final Card TEN_CARD = new Card(Suit.CLUB, Face.TEN);
    static final Card NINE_CARD = new Card(Suit.CLUB, Face.NINE);

    private MockCard() {
    }
}
