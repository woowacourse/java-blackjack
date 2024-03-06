package domain;

import java.util.List;

public class Participants {

    public static final int DEALER_COUNT = 1;
    private final Player dealer;
    private final List<Player> players;


    public Participants(Player dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int count() {
        return players.size() + DEALER_COUNT;
    }
}
