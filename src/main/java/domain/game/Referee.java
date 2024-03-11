package domain.game;

import controller.dto.JudgeResult;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public JudgeResult judge() {
        if (participants.getDealer().isBusted()) {
            return new JudgeResult(participants.getPlayersOutcomeIf(
                    Participant::isNotBusted
            ));
        }
        return new JudgeResult(participants.getPlayersOutcomeIf(
                this::isWinner
        ));
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
