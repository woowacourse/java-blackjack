package domain.game;

import controller.dto.JudgeResult;
import domain.participant.Participants;
import domain.participant.Player;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public JudgeResult judge() {
        if (participants.getDealer().isBusted()) {
            // TODO: 여기 조건에 따르면 참가자는 Bust면 패, Bust가 아니면 승이다.
            return new JudgeResult(participants.getPlayersOutcomeIf(
                    player -> player.isNotBusted()
            ));
        }
        return new JudgeResult(participants.getPlayersOutcomeIf(
                player -> isWinner(player)
        ));
    }

    private boolean isWinner(final Player player) {
        if (player.isBusted()) {
            return true;
        }
        if (player.isNotSameScoreAs(participants.getDealer())) {
            return player.hasMoreScoreThan(participants.getDealer());
        }
        return player.hasLessOrSameCardThan(participants.getDealer());
    }
}
