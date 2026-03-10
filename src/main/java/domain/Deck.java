package domain;

public class Deck {
    private final Cards cards;

    public Deck() {
        Cards deck = Cards.createCards();
        Cards.shuffleCards(deck);
        this.cards = deck;
    }

    // TODO: Deck이 0장이 되었을 때 예외처리
    public Card drawCardFromDeck() {
        Card card = cards.removeFirst();
        return card;
    }
}
