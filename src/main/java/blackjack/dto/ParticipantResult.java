package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public record ParticipantResult(
    String nickname,
    List<String> cardStatus,
    int totalScore
) {

    public static ParticipantResult from(Participant participant) {
        return new ParticipantResult(
            participant.getNickname(),
            participant.getCardStatus(),
            participant.getTotalScore()
        );
    }

    public static ParticipantResult of(String nickname, List<String> cardStatus, int totalScore) {
        return new ParticipantResult(
            nickname,
            cardStatus,
            totalScore
        );
    }
}
