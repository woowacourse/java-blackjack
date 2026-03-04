package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>(Arrays.asList(Card.values()));
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }
}

