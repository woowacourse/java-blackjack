package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void shuffle(final Random random) {
        Collections.shuffle(cards, random);
    }

    public List<Card> getCards() {
        return cards;
    }
}
