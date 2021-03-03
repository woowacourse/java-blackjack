package blackjack;


import java.util.HashMap;
import java.util.Map;

public class Card {
    private static Map<String, Card> cards;

    public static final int CARDS_CAPACITY = 52;

    private final Suits suit;
    private final Denominations denomination;

    static{
        cards = new HashMap<>(CARDS_CAPACITY);
        for (Suits suit : Suits.values()){
            for (Denominations denomination: Denominations.values()){
                String key = denomination.getName() + suit.getName();
                cards.put(key, new Card(suit, denomination));
            }
        }
    }

    private Card(Suits suit, Denominations denomination){
        this.suit = suit;
        this.denomination = denomination;
    }
    public static Card from(String value) {
        Card card = cards.get(value);
        if (card == null) {
            throw new IllegalArgumentException();
        }
        return card;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination == Denominations.ACE;
    }
}
