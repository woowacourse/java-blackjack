package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckGenerator {

    public Deck generate() {
        return new Deck(generateCards());
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardBySuit(cards, suit);
        }
        shuffle(cards);
        return cards;
    }

    private void addCardBySuit(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
