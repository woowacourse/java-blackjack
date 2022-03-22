package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private static final String ERROR_MESSAGE_EMPTY_DECK = "카드가 존재하지 않습니다.";

    private final LinkedList<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = new LinkedList<>(deck);
    }

    public Deck() {
        this(new LinkedList<>(Card.initializeDeck()));
    }

    public static Deck create() {
        List<Card> cards = Card.initializeDeck();
        Collections.shuffle(cards);
        return new Deck(new LinkedList<>(cards));
    }

    public Card distributeCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_DECK);
        }
        return deck.poll();
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }
}
