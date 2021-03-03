package blackjack.domain.carddeck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {
    private static final Deque<Card> CARD_DECK = new ArrayDeque<>();

    private CardDeck() {
    }

    static {
        List<Card> cards = new ArrayList<>();
        for (Pattern pattern : Pattern.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(pattern, number));
            }
        }
        Collections.shuffle(cards);
        CARD_DECK.addAll(cards);
    }

    public static Card draw() {
        return CARD_DECK.pop();
    }
}
