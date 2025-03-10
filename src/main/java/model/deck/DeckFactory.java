package model.deck;

import java.util.Arrays;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;

public class DeckFactory {

    public static List<Card> initializeDeck() {
        return Arrays.stream(CardNumber.values())
                .filter(number -> number != CardNumber.ACE_ONE)
                .flatMap(number -> Arrays.stream(CardShape.values()).map(shape -> new Card(number, shape)))
                .toList();
    }
}
