package blackjack.domain;

public class Game {
    private final Dealer dealer;
    private final Players players;

    private Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Game of(Deck deck, Dealer dealer, Players players) {
        Game game = new Game(dealer, players);
        game.handOutInitialCards(deck);
        return game;
    }

    public GameResult makeGameResult() {
        return GameResult.of(dealer, players);
    }

    private void handOutInitialCards(Deck deck) {
        players.handOutInitialCards(deck);
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
