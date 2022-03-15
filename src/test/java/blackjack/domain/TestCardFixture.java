package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;

import blackjack.domain.card.Card;

public class TestCardFixture {

    public static final Card aceCard = new Card(ACE, CLOVER);
    public static final Card twoCard = new Card(TWO, SPADE);
    public static final Card threeCard = new Card(THREE, DIAMOND);
    public static final Card fourCard = new Card(FOUR, HEART);
    public static final Card fiveCard = new Card(FIVE, CLOVER);
    public static final Card sixCard = new Card(SIX, SPADE);
    public static final Card sevenCard = new Card(SEVEN, DIAMOND);
    public static final Card eightCard = new Card(EIGHT, HEART);
    public static final Card nineCard = new Card(NINE, CLOVER);
    public static final Card tenCard = new Card(TEN, SPADE);
    public static final Card jackCard = new Card(JACK, DIAMOND);
    public static final Card queenCard = new Card(QUEEN, HEART);
    public static final Card kingCard = new Card(KING, CLOVER);
}
