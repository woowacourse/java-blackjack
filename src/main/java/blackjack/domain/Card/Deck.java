package blackjack.domain.Card;

import java.util.*;

public class Deck {

    public static final int INIT_CARD_SIZE = 2;

    private final Queue<Card> deck;

    public Deck() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        deck = new LinkedList<>(cards);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            makeCards(cards, shape);
        }
        return cards;
    }

    private void makeCards(List<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.add(Card.valueOf(shape, number));
        }
    }

    public Cards drawInitCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_SIZE; i++) {
            cards.add(drawOneCard());
        }

        return new Cards(cards);
    }

    public Card drawOneCard() {
        return deck.poll();
    }

    public int size() {
        return deck.size();
    }
}
