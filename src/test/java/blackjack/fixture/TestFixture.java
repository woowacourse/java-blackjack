package blackjack.fixture;

import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Shape;
import java.util.List;

public class TestFixture {

    public static List<Card> provideOneAceCards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.TWO)
        );
    }

    public static List<Card> provideTwoAceCards1() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.TEN)
        );
    }

    public static List<Card> provideTwoAceCards2() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.EIGHT)
        );
    }
}
