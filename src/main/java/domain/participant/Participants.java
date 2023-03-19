package domain.participant;

import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    private Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants create(final Players players) {
        return new Participants(Dealer.create(), players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
