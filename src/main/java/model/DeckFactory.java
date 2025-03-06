package model;

import java.util.ArrayList;
import java.util.List;

public class DeckFactory {

    public static List<Card> initializeDeck() {
        List<Card> deck = new ArrayList<>(52);
        for (CardShape shape : CardShape.values()) {
            for (CardNumber number : CardNumber.values()) {
                if (number.equals(CardNumber.ACE_ONE)) {
                    continue;
                }
                deck.add(new Card(number, shape));
            }
        }
        return deck;
    }
}
