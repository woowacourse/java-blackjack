package domain.participant;

import java.util.List;

public class Participants {

    private final Participant dealer;
    private final Players players;

    private Participants(Participant dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants from(List<String> playersName) {
        return new Participants(new Dealer(), Players.of(playersName));
    }
}
