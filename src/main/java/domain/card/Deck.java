package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> cards;

    public Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public static Deck initDeck() {
        LinkedList<Card> cards = new LinkedList<>();
        for (Symbol symbol : Symbol.values()) {
            addCard(cards, symbol);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void addCard(LinkedList<Card> cards, Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(symbol, denomination));
        }
    }

    public List<Card> handOutInitialTurn(){
        return Arrays.asList(handOut(), handOut());
    }

    public Card handOut(){
        return cards.pop();
    }
}
