package blackjack.domain.participant;

import java.util.List;

public class ParticipantGroup {
    private final Dealer dealer;
    private final List<Player> players;

    public ParticipantGroup(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static ParticipantGroup of(Dealer dealer, List<Player> players) {
        return new ParticipantGroup(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
