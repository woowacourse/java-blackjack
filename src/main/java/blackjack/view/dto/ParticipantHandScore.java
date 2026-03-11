package blackjack.view.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public record ParticipantHandScore(
        String nickname,
        List<String> cardDisplayNames,
        int totalScore
) {
    
    public static ParticipantHandScore from(Participant participant) {
        return new ParticipantHandScore(
                participant.getNickname(),
                toCardDisplayNames(participant),
                participant.getScore()
        );
    }
    
    public static List<ParticipantHandScore> listOf(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantHandScore::from)
                .toList();
    }
    
    private static List<String> toCardDisplayNames(Participant participant) {
        return participant.getCards().stream()
                .map(CardDisplayName::from)
                .map(CardDisplayName::displayName)
                .toList();
    }
}
