package model.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;

public class DeckGenerator {

    public List<Card> getInitializedDeck() {
        List<Card> initialDeck = new ArrayList<>(Arrays.stream(CardNumber.values())
                .filter(number -> number != CardNumber.ACE_ONE)
                .flatMap(number -> Arrays.stream(CardShape.values()).map(shape -> new Card(number, shape)))
                .toList());

        shuffle(initialDeck);
        return initialDeck;
    }

    private void shuffle(final List<Card> initialDeck) {
        Collections.shuffle(initialDeck);
    }
}
