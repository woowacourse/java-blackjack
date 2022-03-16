package domain.card;

import static utils.ExceptionMessages.CAN_NOT_POP_CARD_ERROR;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    private Deck(List<Card> cards) {
        deck = new LinkedList<>(cards);
    }

    public static Deck of(List<Card> cards) {
        return new Deck(cards);
    }

    public List<Card> handOutInitialTurn() {
        return Arrays.asList(handOut(), handOut());
    }

    public Card handOut() {
        checkCardSize();
        return deck.pop();
    }

    private void checkCardSize() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(CAN_NOT_POP_CARD_ERROR);
        }
    }

    public int size() {
        return deck.size();
    }
}
