package blackjack.domain.gamer;

import java.util.List;

public class Gamers {

    private Dealer dealer;
    private Players players;

    private Gamers(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = Players.of(players);
    }

    public static Gamers of(Dealer dealer, List<Player> players) {
        return new Gamers(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
