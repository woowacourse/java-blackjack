package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;

import java.util.List;

public class CardBundleFixture {

    public static Cards JACK_ACE_BLACKJACK = new Cards(List.of(
            Card.valueOf(Shape.CLOVER, Number.JACK),
            Card.valueOf(Shape.CLOVER, Number.ACE))
    );

    public static Cards _21_OVER_CARDS = new Cards(List.of(
            Card.valueOf(Shape.CLOVER, Number.JACK),
            Card.valueOf(Shape.CLOVER, Number.TEN),
            Card.valueOf(Shape.HEART, Number.EIGHT))
    );

    public static Cards _21_UNDER_CARDS = new Cards(List.of(
            Card.valueOf(Shape.CLOVER, Number.FIVE),
            Card.valueOf(Shape.CLOVER, Number.ACE))
    );

    public static Cards _21_NOT_BLACKJACK = new Cards(List.of(
            Card.valueOf(Shape.HEART, Number.JACK),
            Card.valueOf(Shape.DIAMOND, Number.EIGHT),
            Card.valueOf(Shape.HEART, Number.THREE))
    );
}
