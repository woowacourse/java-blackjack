package blackjack.domain.card;

public class CardDeck {
    private final Cards deck;

    public CardDeck(Cards deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void shuffleCard() {
        deck.shuffle();
    }
}
