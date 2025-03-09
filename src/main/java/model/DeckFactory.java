package model;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    public static List<Card> initializeDeck() {
        List<Card> deck = new ArrayList<>(52);
        for (CardShape shape : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                addCardIfShapeNotAceOne(shape, number, deck);
            }
        }
        return deck;
    }

    private static void addCardIfShapeNotAceOne(CardShape shape, CardNumber number, List<Card> deck) {
        if (!number.equals(CardNumber.ACE_ONE)) {
            deck.add(new Card(number, shape));
        }
    }
}
