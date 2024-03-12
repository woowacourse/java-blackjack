package domain.game;

import controller.dto.PlayerOutcome;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public List<PlayerOutcome> judge() {
        if (participants.getDealer().isBusted()) {
            return participants.getPlayersOutcomeIf(
                    Participant::isNotBusted
            );
        }
        return participants.getPlayersOutcomeIf(
                this::isWinner
        );
    }

    private boolean isWinner(final Player player) {
        if (player.isBusted()) {
            return false;
        }
        if (player.isNotSameScoreAs(participants.getDealer())) {
            return player.hasMoreScoreThan(participants.getDealer());
        }
        return player.hasLessOrSameCardThan(participants.getDealer());
    }
}
