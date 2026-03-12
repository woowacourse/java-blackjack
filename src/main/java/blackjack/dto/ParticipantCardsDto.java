package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public record ParticipantCardsDto(
    String participantName,
    List<CardDto> cards
) {
    public static ParticipantCardsDto from(Participant participant) {
        List<CardDto> cards = participant.getCards().stream()
            .map(CardDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }

    public static ParticipantCardsDto of(Participant participant, long size) {
        List<CardDto> cards = participant.getCards().stream()
            .limit(size)
            .map(CardDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }
}
