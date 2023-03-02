package domain;

public class CardDistributor {

    private final CardDeck cardDeck;

    public CardDistributor(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card pick() {
        return cardDeck.pick();
    }

}
