package domain;

import controller.dto.JudgeResult;
import domain.participant.Participant;
import domain.participant.Participants;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public JudgeResult judge() {
        if (participants.getDealer().isBusted()) {
            // TODO: 여기 조건에 따르면 참가자는 Bust면 패, Bust가 아니면 승이다.
            return new JudgeResult(participants.getParticipantOutcomesIf(
                    participant -> participant.isNotBusted()
            ));
        }
        return new JudgeResult(participants.getParticipantOutcomesIf(
                participant -> isWinner(participant)
        ));
    }

    private boolean isWinner(final Participant participant) {
        if (participant.isBusted()) {
            return true;
        }
        if (participant.isNotSameScoreAs(participants.getDealer())) {
            return participant.hasMoreScoreThan(participants.getDealer());
        }
        return participant.hasLessOrSameCardThan(participants.getDealer());
    }
}
