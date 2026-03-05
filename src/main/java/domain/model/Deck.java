package domain.model;

import java.util.List;

public class Deck {

    private DeckStatus deckStatus;
    private List<Card> cards;

    private Deck(List<Card> deck) {
        this.cards = deck;
    }

    public static Deck of(List<Card> cards) {
        return new Deck(cards);
    }

    public int getSum() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public void append(Card card) {
        if (deckStatus.equals(DeckStatus.ALIVE)) {
            this.cards.add(card);
        }
        checkStatus();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getLastCard() {
        return cards.getLast();
    }

    private void checkStatus() {
        if (getSum() > 21) {
            deckStatus = DeckStatus.BURST;
        }
    }
}
