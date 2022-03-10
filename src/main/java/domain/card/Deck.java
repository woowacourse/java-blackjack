package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final LinkedList<Card> CACHE = new LinkedList<>();

    static {
        for (Symbol symbol : Symbol.values()) {
            addCard(symbol);
        }
        Collections.shuffle(CACHE);
    }

    private static void addCard(Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            CACHE.add(new Card(symbol, denomination));
        }
    }

    public static List<Card> handOutInitialTurn(){
        return Arrays.asList(handOut(), handOut());
    }

    public static Card handOut(){
        return CACHE.pop();
    }
}
