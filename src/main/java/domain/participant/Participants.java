package domain.participant;

import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players participants) {
        this.dealer = dealer;
        this.players = participants;
    }

    public static Participants from(List<String> playerNames) {
        return new Participants(new Dealer(), Players.from(playerNames));
    }

    public boolean doesNotContain(Participant participant) {
        if (participant == dealer) {
            return false;
        }
        return players.doesNotContain(participant);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
