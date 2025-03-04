package blackjack.model;

public class Dealer {

    private final CardDeck cardDeck;

    public Dealer(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Cards draw(final int drawSize) {
        return new Cards(cardDeck.draw(drawSize));
    }

}
