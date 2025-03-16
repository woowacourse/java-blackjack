package domain;

import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Participants() {
        this(Dealer.createEmpty(), new Players());
    }

    public void registerPlayers(List<Player> players) {
        this.players.addAll(players);
    }
}
