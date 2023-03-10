package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackjackParticipants {
    private final Dealer dealer = new Dealer();
    private final Players players;

    public BlackjackParticipants(Players players) {
        this.players = players;
    }

    public static BlackjackParticipants from(Players players) {
        return new BlackjackParticipants(players);
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
