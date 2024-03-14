package util;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.List;

public final class CardsSupplier {
    public static List<Card> createNaturalBlackJackCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.ACE, Suit.DIAMOND));
    }

    public static List<Card> createBlackJackCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.THREE, Suit.DIAMOND),
                new Card(Denomination.EIGHT, Suit.SPADE));
    }

    public static List<Card> createSevenScoreWithTwoCards() {
        return List.of(
                new Card(Denomination.TWO, Suit.CLOVER),
                new Card(Denomination.FIVE, Suit.DIAMOND));
    }

    public static List<Card> createSixteenScoreWithTwoCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.SIX, Suit.DIAMOND));
    }

    public static List<Card> createNineteenScoreWithThreeCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.THREE, Suit.DIAMOND),
                new Card(Denomination.SIX, Suit.HEART));
    }

    public static List<Card> createNineteenScoreNormalWithTwoCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.NINE, Suit.DIAMOND));
    }

    public static List<Card> createBustedCards() {
        return List.of(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.FIVE, Suit.DIAMOND),
                new Card(Denomination.EIGHT, Suit.HEART));
    }
}
