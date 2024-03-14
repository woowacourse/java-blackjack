package domain.card;

import java.util.*;

public class Deck {

    public static final int DECK_TOP = 0;
    private final List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
        createDeck();
    }

    private void createDeck() {
        for (CardNumber value : CardNumber.values()) {
            createCardByNumberAndShape(value);
        }
        Collections.shuffle(deck);
    }

    private void createCardByNumberAndShape(CardNumber value) {
        for (Shape shape : Shape.values()) {
            deck.add(new Card(value.getValue(), shape));
        }
    }

    public List<Card> drawInitialHands() {
        return List.of(draw(), draw());
    }

    public Card draw() {
        if(deck.isEmpty()){
            throw new NoSuchElementException("덱에 남아있는 카드가 없습니다.");
        }
        Card card = deck.get(DECK_TOP);
        deck.remove(DECK_TOP);
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck1 = (Deck) o;
        return Objects.equals(deck, deck1.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}
