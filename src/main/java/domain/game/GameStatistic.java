package domain.game;

import domain.player.Dealer;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatistic {

    private final Dealer dealer;
    private final List<Player> players;
    private final Map<Player, ParticipantResult> resultPerParticipant;

    public GameStatistic(final Dealer dealer, final List<Player> players, final Map<Player, ParticipantResult> resultPerParticipant) {
        this.dealer = dealer;
        this.players = players;
        this.resultPerParticipant = resultPerParticipant;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Player> participants() {
        return new ArrayList<>(players);
    }

    public Map<Player, ParticipantResult> resultPerParticipant() {
        return resultPerParticipant;
    }
}
