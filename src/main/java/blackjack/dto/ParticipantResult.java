package blackjack.dto;

import blackjack.domain.participant.Participant;

public record ParticipantResult(
    String nickname,
    String cardStatus,
    int totalScore
) {

    public ParticipantResult(Participant participant) {
        this(
            participant.getNickname(),
            participant.getCardStatus(),
            participant.getTotalScore()
        );
    }

    @Override
    public String toString() {
        return String.format("%s카드: %s", nickname, cardStatus);
    }

    public String toFullString() {
        return String.format("%s카드: %s - 결과: %d", nickname, cardStatus, totalScore);
    }
}
