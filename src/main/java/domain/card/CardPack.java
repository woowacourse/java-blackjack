package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        this.cards = new ArrayList<>(Arrays.asList(Card.values()));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        return cards.removeFirst();
    }

    public int size() {
        return cards.size();
    }
}
