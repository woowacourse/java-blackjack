package BlackJack.domain.utils;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.Card.Number;
import BlackJack.domain.Card.Shape;

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
