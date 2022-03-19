package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;

import java.util.List;

public class CardBundleFixture {

    public static Cards JACK_ACE_BLACKJACK = new Cards(List.of(
            new Card(Shape.CLOVER, Number.JACK),
            new Card(Shape.CLOVER, Number.ACE))
    );

    public static Cards _21_OVER_CARDS = new Cards(List.of(
            new Card(Shape.CLOVER, Number.JACK),
            new Card(Shape.CLOVER, Number.TEN),
            new Card(Shape.HEART, Number.EIGHT))
    );

    public static Cards _21_UNDER_CARDS = new Cards(List.of(
            new Card(Shape.CLOVER, Number.FIVE),
            new Card(Shape.CLOVER, Number.ACE))
    );

    public static Cards _21_NOT_BLACKJACK = new Cards(List.of(
            new Card(Shape.HEART, Number.JACK),
            new Card(Shape.DIAMOND, Number.EIGHT),
            new Card(Shape.HEART, Number.THREE))
    );
}
