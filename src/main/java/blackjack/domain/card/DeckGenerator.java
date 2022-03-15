package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DeckGenerator {
    
    public DeckGenerator() {

    }

    public Deque<Card> generateDeck() {
        List<Card> cards = Card.cache();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }
}
