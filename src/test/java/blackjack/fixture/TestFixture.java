package blackjack.fixture;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Shape;
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
