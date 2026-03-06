package blackjack;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void initDraw() {
        for(int i = 0; i < 2; i++) {
            players.recieveCard(deck.draw());
            dealer.recieveCard(deck.draw());
        }
    }

}
