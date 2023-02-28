package domain;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (CardSuit suit : CardSuit.values()) {
            generateCard(suit);
        }
    }

    private final CardSuit cardSuit;
    private final CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    private static void generateCard(CardSuit suit) {
        for (CardNumber number : CardNumber.values()) {
            CACHE.add(new Card(suit, number));
        }
    }

    public static List<Card> values() {
        return List.copyOf(CACHE);
    }

    public CardSuit getSuit() {
        return cardSuit;
    }

    public CardNumber getNumber() {
        return cardNumber;
    }
}
