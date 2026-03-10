package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public record ParticipantHandScore(
        String nickname,
        List<String> cardNames,
        int totalScore
) {
    public static ParticipantHandScore from(Participant participant) {
        return new ParticipantHandScore(
                participant.getNickname(),
                participant.getCards(),
                participant.getTotalScore()
        );
    }

    public static List<ParticipantHandScore> listOf(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantHandScore::from)
                .toList();
    }
}
