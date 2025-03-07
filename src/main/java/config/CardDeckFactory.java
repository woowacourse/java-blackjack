package config;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Number;
import domain.card.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {

    public CardDeck create() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            selectNumbers(shape, cards);
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static void selectNumbers(Shape shape, List<Card> cards) {
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
    }
}
