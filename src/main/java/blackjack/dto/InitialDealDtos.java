package blackjack.dto;

import blackjack.model.Participants;
import java.util.List;

public record InitialDealDtos(
    ParticipantCardsDto dealerCardsDto,
    List<ParticipantCardsDto> playerCardsDtos
) {
    public static InitialDealDtos from(Participants participants) {
        ParticipantCardsDto dealerCardsDto = ParticipantCardsDto.fromAllCards(
            participants.getDealer());
        List<ParticipantCardsDto> playerCardsDto = participants.getPlayers().stream()
            .map(ParticipantCardsDto::fromVisibleCards)
            .toList();
        return new InitialDealDtos(dealerCardsDto, playerCardsDto);
    }
}
