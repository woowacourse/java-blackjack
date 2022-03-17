package blackjack.domain.entry;

import blackjack.domain.GameResult;
import blackjack.domain.PlayerOutcome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
