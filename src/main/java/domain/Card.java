package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Card {

    private static final Queue<Card> CACHE;

    private final Symbol symbol;
    private final Denomination denomination;


    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    static {
        List<Card> tmpCards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            addCard(symbol, tmpCards);
        }
        Collections.shuffle(tmpCards);
        CACHE = new LinkedList<>(tmpCards);
    }

    private static void addCard(Symbol symbol, List<Card> tmpCards) {
        for (Denomination denomination : Denomination.values()) {
            tmpCards.add(new Card(symbol, denomination));
        }
    }

    public static Card draw(){
        return CACHE.remove();
    }

    public int getScore(){
        return denomination.getScore();
    }
}
