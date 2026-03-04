package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        cards = initialize();
    }

    private List<Card> initialize() {
        List<Card> results = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                results.add(new Card(cardNumber, cardShape));
            }
        }
        return results;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
