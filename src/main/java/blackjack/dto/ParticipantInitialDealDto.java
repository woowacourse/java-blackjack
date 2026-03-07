package blackjack.dto;

import blackjack.model.Participant;
import java.util.List;

public record ParticipantInitialDealDto(
    String participantName,
    List<CardDto> cards
) {
    public static ParticipantInitialDealDto from(Participant participant) {
        List<CardDto> cards = participant.getVisibleCards()
            .stream()
            .map(CardDto::from)
            .toList();
        return new ParticipantInitialDealDto(participant.getName(), cards);
    }
}
