package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public List<Participant> all() {
        final List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players.all());

        return participants;
    }
}
