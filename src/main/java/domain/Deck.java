package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private static Queue<Card> DECK = createDeck();

    public Deck() {
        DECK = createDeck();
    }

    private static Queue<Card> createDeck() {
        List<Card> deck = new LinkedList<>();
        addCardsInDeck(deck);
        Collections.shuffle(deck);

        return (Queue<Card>) deck;
    }

    private static void addCardsInDeck(List<Card> deck) {
        Arrays.stream(Denomination.values()).forEach(denomination -> {
            Arrays.stream(Suit.values()).map(suit -> new Card(suit, denomination)).forEach(deck::add);
        });
    }

    public static Card pickCard() {
        return DECK.poll();
    }
}
