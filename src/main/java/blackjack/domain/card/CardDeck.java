package blackjack.domain.card;

public class CardDeck {
    private Cards deck;

    public CardDeck(Cards deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card drawCard = deck.peekCard();
        this.deck = deck.removeCard();
        return drawCard;
    }

    public void shuffleCard() {
        deck.shuffle();
    }
}
