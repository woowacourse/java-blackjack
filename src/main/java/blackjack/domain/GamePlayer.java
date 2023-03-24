package blackjack.domain;

public class GamePlayer {
    private final Dealer dealer;
    private final Players players;

    public GamePlayer(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
