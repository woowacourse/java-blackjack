package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Deck {

    private final static List<Card> CACHE = new ArrayList<>();

    private final LinkedList<Card> cards;

    private Deck(LinkedList<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    static {
        Arrays.stream(Symbol.values())
            .forEach(symbol -> Arrays.stream(Denomination.values())
                .forEach(denomination -> CACHE.add(new Card(symbol, denomination))));
    }

    public static Deck initDeck() {
        LinkedList<Card> cards = new LinkedList<>(CACHE);
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public List<Card> handOutCards() {
        return Arrays.asList(handOut(), handOut());
    }

    public Card handOut() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
