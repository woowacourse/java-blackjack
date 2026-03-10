package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        this.cards = initialize();
    }

    public Card draw() {
        return cards.pop();
    }

    private Deque<Card> initialize() {
        Deque<Card> results = new ArrayDeque<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(cardNumber -> results.add(new Card(cardNumber, cardSuit)));
        }
        return shuffle(results);
    }

    private ArrayDeque<Card> shuffle(Deque<Card> results) {
        List<Card> shuffled = new ArrayList<>(results);
        Collections.shuffle(shuffled);

        return new ArrayDeque<>(shuffled);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
