package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {
    private final Dealer dealer = new Dealer();
    private final Players players;

    public Participants(Players players) {
        this.players = players;
    }

    public static Participants from(Players players) {
        return new Participants(players);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Participant> getAllParticipants() {
        List<Participant> allParticipants = new ArrayList<>();
        allParticipants.add(dealer);
        allParticipants.addAll(players.getPlayers());

        return allParticipants;
    }
}
