package blackjack.domain.card;

import java.util.*;

public class Deck {
    private final Queue<Card> deck;

    private Deck(Queue<Card> deck) {
        this.deck = deck;
    }

    public static Deck create() {
        List<Card> deck = new ArrayList<>(Card.createDeck());
        Collections.shuffle(deck);
        return new Deck(new LinkedList<>(deck));
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("더 이상 꺼낼 카드가 존재하지 않습니다.");
        }

        return deck.poll();
    }
}
