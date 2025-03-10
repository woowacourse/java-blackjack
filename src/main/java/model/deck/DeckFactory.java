package model.deck;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;

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
