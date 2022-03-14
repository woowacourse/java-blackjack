package domain.card;

import static utils.ExceptionMessages.CAN_NOT_POP_CARD_ERROR;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    private Deck(LinkedList<Card> cards) {
        deck = cards;
    }

    public static Deck getInstance() {
        LinkedList<Card> tmpCards = new LinkedList<>();
        for (Symbol symbol : Symbol.values()) {
            addCard(symbol, tmpCards);
        }
        Collections.shuffle(tmpCards);
        return new Deck(tmpCards);
    }

    private static void addCard(Symbol symbol, LinkedList<Card> tmpCards) {
        for (Denomination denomination : Denomination.values()) {
            tmpCards.add(Card.of(symbol, denomination));
        }
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
    public int size(){
        return deck.size();
    }
}
