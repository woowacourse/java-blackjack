package domain.card;

import java.util.ArrayList;
import java.util.List;

public class DeckGenerator {

    public static Deck generate() {
        return new Deck(generateCards());
    }

    private static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardBySuit(cards, suit);
        }
        return cards;
    }

    private static void addCardBySuit(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }
}
