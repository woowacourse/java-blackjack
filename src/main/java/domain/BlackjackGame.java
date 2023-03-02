package domain;

public class BlackjackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public void initialize(Deck deck, ShuffleStrategy shuffleStrategy) {
        deck.shuffle(shuffleStrategy);
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        players.receiveTwoCards(deck);
    }
}
