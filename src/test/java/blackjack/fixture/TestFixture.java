package blackjack.fixture;

import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Shape;
import blackjack.cardMachine.CardMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFixture {

    public static class TestCardRandomMachine implements CardMachine {

        private final List<Card> deck = List.of(
                new Card(Shape.CLOB, Denomination.ACE),
                new Card(Shape.CLOB, Denomination.TWO),
                new Card(Shape.CLOB, Denomination.THREE)
        );

        @Override
        public void receiveDeck(final List<Card> deck) {

        }

        @Override
        public Card drawOneCard() {
            final List<Card> mutableDeck = new ArrayList<>(deck);
            Collections.shuffle(mutableDeck);
            return mutableDeck.getFirst();
        }

    }

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
}
