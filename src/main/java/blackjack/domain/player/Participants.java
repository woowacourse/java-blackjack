package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }
}
