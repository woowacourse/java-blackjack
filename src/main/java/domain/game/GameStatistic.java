package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;
import java.util.Map;

public class GameStatistic {

    private final Dealer dealer;
    private final List<Participant> participants;
    private final Map<Participant, PlayerResult> resultPerParticipant;

    public GameStatistic(final Dealer dealer, final List<Participant> participants, final Map<Participant, PlayerResult> resultPerParticipant) {
        this.dealer = dealer;
        this.participants = participants;
        this.resultPerParticipant = resultPerParticipant;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Participant> participants() {
        return participants;
    }

    public Map<Participant, PlayerResult> resultPerParticipant() {
        return resultPerParticipant;
    }
}
