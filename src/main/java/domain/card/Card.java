package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Card {

    private static final LinkedList<Card> CACHE = new LinkedList<>();

    private final Symbol symbol;
    private final Denomination denomination;


    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

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

    public static Card draw(){
        return CACHE.pop();
    }

    public static List<Card> handOutInitialTurn(){
        return Arrays.asList(draw(), draw());
    }

    public int getScore(){
        return denomination.getScore();
    }

    public String getDenomination(){
        return denomination.getLetter();
    }

    public String getSymbol() {
        return symbol.getLetter();
    }
}
