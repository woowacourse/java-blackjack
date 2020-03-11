package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final int INIT_CARD_POSITION = 0;
    private final List<Card> cardDeck = new ArrayList<>();

    public CardDeck(final List<Card> cardDeck) {
        this.cardDeck.addAll(cardDeck);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public Card drawOne() {
        return cardDeck.remove(INIT_CARD_POSITION);
    }
}
