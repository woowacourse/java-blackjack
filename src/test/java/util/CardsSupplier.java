package util;

import domain.card.Card;
import domain.card.Score;
import domain.card.Shape;
import java.util.List;

public final class CardsSupplier {
    public static List<Card> createBlackJackWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    public static List<Card> createBlackJackWithThreeCards() {
        return List.of(
                new Card(Score.FIVE, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    public static List<Card> createSevenScoreWithTwoCards() {
        return List.of(
                new Card(Score.TWO, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND));
    }

    public static List<Card> createSixteenScoreWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND));
    }

    public static List<Card> createNineteenScoreWithThreeCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.SIX, Shape.HEART));
    }

    public static List<Card> createNineteenScoreNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.NINE, Shape.DIAMOND));
    }

    public static List<Card> createBustedCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART));
    }
}
