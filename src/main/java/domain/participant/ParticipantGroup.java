package domain.participant;

import java.util.List;

public class ParticipantGroup {
    private final Players players;
    private final Dealer dealer;

    public ParticipantGroup(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Players getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
