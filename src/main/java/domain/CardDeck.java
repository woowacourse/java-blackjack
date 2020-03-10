package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> cardDeck = new ArrayList<>();

    public CardDeck(List<Card> cardDeck) {
        this.cardDeck.addAll(cardDeck);
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public Card drawOne() {
        return cardDeck.remove(0);
    }
}
