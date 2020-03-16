package domains.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    public static final int FIRST_INDEX = 0;
    public static final int INITIAL_DRAW_SIZE = 2;
    private List<Card> deck;

    public Deck() {
        this.deck = DeckFactory.create();
    }

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new InvalidDeckException(InvalidDeckException.DECK_IS_EMPTY);
        }
        return deck.remove(FIRST_INDEX);
    }

    public List<Card> initialDraw() {
        List<Card> initialCards = deck.subList(FIRST_INDEX, INITIAL_DRAW_SIZE);
        List<Card> hands = new ArrayList<>(initialCards);

        initialCards.clear();

        return hands;
    }

    public boolean isSize(int size) {
        return deck.size() == size;
    }
}
