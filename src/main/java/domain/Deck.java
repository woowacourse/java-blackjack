package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final Random random = new Random();
    private final List<Card> cards;

    public Deck() {
        this.cards = buildDeck();
    }

    private List<Card> buildDeck() {
        var cards = new ArrayList<Card>();
        var faces = List.of(Face.values());

        for (Face face : faces) {
            cards.addAll(buildCardsFrom(face));
        }

        return cards;
    }

    private List<Card> buildCardsFrom(Face face) {
        var cards = new ArrayList<Card>();
        var letters = List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J");

        for (String letter : letters) {
            cards.add(new Card(face, letter));
        }

        return cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        validateNotEmpty();

        return cards.remove(pickRandomCard());
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
    }

    private int pickRandomCard() {
        return random.nextInt(cards.size());
    }

}
