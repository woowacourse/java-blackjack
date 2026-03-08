package blackjack.dto;

import blackjack.model.Participant;
import java.util.List;

public record ParticipantCardsDto(
    String participantName,
    List<CardDto> cards
) {
    public static ParticipantCardsDto fromAllCards(Participant participant) {
        List<CardDto> cards = participant.getCards()
            .stream()
            .map(CardDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }

    public static ParticipantCardsDto fromVisibleCards(Participant participant) {
        List<CardDto> cards = participant.getVisibleCards()
            .stream()
            .map(CardDto::from)
            .toList();
        return new ParticipantCardsDto(participant.getName(), cards);
    }
}
