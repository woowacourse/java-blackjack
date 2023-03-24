package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int OFFSET_BETWEEN_SIZE_AND_INDEX = 1;

    private final List<Card> cards;

    public Deck() {
        this.cards = shuffle(buildDeck());
    }

    private List<Card> shuffle(List<Card> cards) {
        List<Card> cardsToShuffle = new ArrayList<>(cards);
        Collections.shuffle(cardsToShuffle);
        return cardsToShuffle;
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
            cards.add(Card.of(face, letter));
        }
        return cards;
    }

    public Card draw() {
        validateNotEmpty();
        int cardIndexOnTop = cards.size() - OFFSET_BETWEEN_SIZE_AND_INDEX;
        return cards.remove(cardIndexOnTop);
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
