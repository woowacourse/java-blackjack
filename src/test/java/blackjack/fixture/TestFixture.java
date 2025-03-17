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

    public static List<Card> provideBlackjackCards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.TEN)
        );
    }

    public static List<Card> provideFourSizeCards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.TEN),
                new Card(Shape.HEART, Denomination.TWO),
                new Card(Shape.CLOB, Denomination.SEVEN)
        );
    }

    public static List<Card> provideSum15Cards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.SIX),
                new Card(Shape.CLOB, Denomination.NINE)
        );
    }

    public static List<Card> provideSum17Cards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.EIGHT),
                new Card(Shape.CLOB, Denomination.NINE)
        );
    }

    public static List<Card> provideSum18Cards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.ACE),
                new Card(Shape.CLOB, Denomination.SEVEN)
        );
    }

    public static List<Card> provideSum20Cards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.KING),
                new Card(Shape.CLOB, Denomination.TEN)
        );
    }

    public static List<Card> provideBustCards() {
        return List.of(
                new Card(Shape.DIAMOND, Denomination.SEVEN),
                new Card(Shape.CLOB, Denomination.TEN),
                new Card(Shape.CLOB, Denomination.EIGHT)
        );
    }
}
