package domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDistributor {
    private static final Stack<Card> deck = new Stack<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                deck.push(new Card(denomination, suit));
            }
        }
        Collections.shuffle(deck);
    }

    public Card distribute() {
        if (deck == null || deck.empty()) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return deck.pop();
    }
}
