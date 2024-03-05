package domain;

public class Dealer {

    private final Players players;
    private final CardDeck cardDeck;

    public Dealer(final Players players, final CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void startDeal() {
        cardDeck.shuffle();

    }
}
