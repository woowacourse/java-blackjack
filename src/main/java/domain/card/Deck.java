package domain.card;

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
        List<Card> cards = new ArrayList<>();
        for (Face face : Face.values()) {
            cards.addAll(buildCardsOf(face));
        }
        return cards;
    }

    private List<Card> buildCardsOf(Face face) {
        List<Card> cards = new ArrayList<>();
        for (Letter letter : Letter.values()) {
            cards.add(new Card(face, letter));
        }
        return cards;
    }

    public Card draw() {
        validateNotEmpty();
        return cards.remove(pickRandomIndex());
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
    }

    private int pickRandomIndex() {
        return random.nextInt(cards.size());
    }

    public List<Card> getCards() {
        return cards;
    }
}
