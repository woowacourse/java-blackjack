package domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gamers {

    Players players;
    Dealer dealer;

    public Gamers(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Player> allGamers() {
        List<Player> allGamers = new ArrayList<>(players.getPlayers());
        allGamers.add(dealer);
        return Collections.unmodifiableList(allGamers);
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Player> players() {
        return players.getPlayers();
    }
}
