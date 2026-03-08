package blackjack.dto;

import blackjack.model.Participants;
import java.util.List;

public record ParticipantInitialDealDtos(
    List<ParticipantCardsDto> participantCardsDtos
) {
    public static ParticipantInitialDealDtos from(Participants participants) {
        return new ParticipantInitialDealDtos(
            participants.stream().map(ParticipantCardsDto::fromVisibleCards).toList());
    }
}
