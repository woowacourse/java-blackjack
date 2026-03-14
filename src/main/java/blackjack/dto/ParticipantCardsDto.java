package blackjack.dto;

import blackjack.domain.participants.Participant;
import java.util.List;

public record ParticipantCardsDto(
    String participantName,
    List<CardNameDto> cards
) {
    public static ParticipantCardsDto from(Participant participant) {
        List<CardNameDto> cards = participant.getCards().stream()
            .map(CardNameDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }

    public static ParticipantCardsDto of(Participant participant, long size) {
        List<CardNameDto> cards = participant.getCards().stream()
            .limit(size)
            .map(CardNameDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }
}
