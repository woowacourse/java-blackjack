package blackjack.domain.entry;

import blackjack.domain.result.GameResult;

import java.util.ArrayList;
import java.util.List;

public class GameParticipants {
    private final Dealer dealer;
    private final Players players;

    public GameParticipants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public GameResult getGameResult() {
        return new GameResult(players.match(dealer));
    }

    public List<Participant> getParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
