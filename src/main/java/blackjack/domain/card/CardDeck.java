package blackjack.domain.card;

public class CardDeck {
    private Cards deck;

    public CardDeck() {
        deck = new Cards(Cards.values());
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
