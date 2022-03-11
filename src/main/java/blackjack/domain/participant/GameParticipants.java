package blackjack.domain.participant;

import java.util.List;

public class GameParticipants {

    private final Dealer dealer;
    private final List<Player> players;

    private GameParticipants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameParticipants of(Dealer dealer, List<Player> players) {
        return new GameParticipants(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "GameParticipants{" +
                "dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
