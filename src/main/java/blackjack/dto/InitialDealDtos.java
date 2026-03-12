package blackjack.dto;

import blackjack.domain.participant.Participants;
import java.util.List;

public record InitialDealDtos(
    ParticipantCardsDto dealerCardsDto,
    List<ParticipantCardsDto> playerCardsDtos
) {
    private static final int DEALER_VISIBLE_CARD_SIZE = 1;

    public static InitialDealDtos from(Participants participants) {
        ParticipantCardsDto dealerCardsDto = ParticipantCardsDto.of(
            participants.getDealer(), DEALER_VISIBLE_CARD_SIZE);

        List<ParticipantCardsDto> playerCardsDto = participants.getPlayers().stream()
            .map(ParticipantCardsDto::from)
            .toList();
        return new InitialDealDtos(dealerCardsDto, playerCardsDto);
    }
}
