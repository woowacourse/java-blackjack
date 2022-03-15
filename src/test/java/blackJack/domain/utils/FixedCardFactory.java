package blackJack.domain.utils;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.CardFactory;
import blackJack.domain.Card.Cards;
import blackJack.domain.Card.Shape;

import java.util.List;

public class FixedCardFactory extends CardFactory {
    @Override
    public Cards initCards() {
        return new Cards(List.of(new Card(Shape.HEART, Number.TEN), new Card(Shape.CLOVER, Number.FIVE)));
    }

    @Override
    public Card drawOneCard() {
        return new Card(Shape.DIAMOND, Number.FIVE);
    }
}
