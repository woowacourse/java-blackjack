package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private static List<Card> cards;

    private Cards() {
    }

    public static List<Card> selectRandomCards(int size) {
        if (cards == null) {
            Cards.createCards();
        }
        return Stream.generate(Cards::selectRandomCard).limit(size).toList();
    }

    private static void createCards() {
        cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            createSameShapeCards(cardShape);
        }
    }

    private static void createSameShapeCards(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    private static Card selectRandomCard() {
        Collections.shuffle(cards);
        return cards.remove(0);
    }
}
