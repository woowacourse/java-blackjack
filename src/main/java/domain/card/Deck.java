package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    private Deck() {
        LinkedList<Card> tmpCards = new LinkedList<>();
        for (Symbol symbol : Symbol.values()) {
            addCard(symbol, tmpCards);
        }
        Collections.shuffle(tmpCards);
        deck = tmpCards;
    }

    public static Deck of() {
        return new Deck();
    }

    private void addCard(Symbol symbol, LinkedList<Card> tmpCards) {
        for (Denomination denomination : Denomination.values()) {
            tmpCards.add(Card.of(symbol, denomination));
        }
    }

    public List<Card> handOutInitialTurn() {
        return Arrays.asList(handOut(), handOut());
    }

    public Card handOut() {
        return deck.pop();
    }
}
