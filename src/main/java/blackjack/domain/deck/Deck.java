package blackjack.domain.deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM  = new Random();

    private final List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public Card pickRandomCard() {
        validateDeck();
        int pickCardIndex = RANDOM.nextInt(deck.size());
        return deck.remove(pickCardIndex);
    }

    private void validateDeck() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    public int size() {
        return deck.size();
    }

    public List<Card> getDeck() {
        return deck;
    }
}
