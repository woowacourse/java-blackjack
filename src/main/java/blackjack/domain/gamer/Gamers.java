package blackjack.domain.gamer;

import java.util.List;

public class Gamers {

    private final List<Player> players;
    private final Dealer dealer;


    public Gamers(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void compare() {

    }

    public List<Player> players() {
        return players;
    }

    public Dealer dealer() {
        return dealer;
    }
}
