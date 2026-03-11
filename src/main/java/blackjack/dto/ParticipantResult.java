package blackjack.dto;

import blackjack.domain.participant.Participant;

public record ParticipantResult(
    String nickname,
    String cardStatus,
    int totalScore
) {

    public static ParticipantResult from(Participant participant) {
        return new ParticipantResult(
            participant.getNickname(),
            participant.getCardStatus(),
            participant.getTotalScore()
        );
    }

    public static ParticipantResult of(String nickname, String cardStatus, int totalScore) {
        return new ParticipantResult(
            nickname,
            cardStatus,
            totalScore
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
