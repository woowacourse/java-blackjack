package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class DeckMaker {
    public List<Card> makeDeck() {
        final List<Card> newDeck = new ArrayList<>();
        for (Letter letter : Letter.values()) {
            makeNewDeck(newDeck, letter);
        }
        return newDeck;
    }

    private static void makeNewDeck(List<Card> newDeck, Letter letter) {
        for (Shape shape : Shape.values()) {
            newDeck.add(new Card(shape, letter));
        }
    }
}
