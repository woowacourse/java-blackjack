package blackjack.domain.utils;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Deck;
import blackjack.domain.Card.Cards;
import blackjack.domain.Card.Shape;
import blackjack.domain.Card.Number;
import java.util.List;

public class FixedCardFactory extends Deck {
    @Override
    public Cards drawInitCards() {
        return new Cards(List.of(new Card(Shape.HEART, Number.TEN), new Card(Shape.CLOVER, Number.FIVE)));
    }

    @Override
    public Card drawOneCard() {
        return new Card(Shape.DIAMOND, Number.FIVE);
    }
}
