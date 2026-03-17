package blackjack.dto;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;

public record InitialDealDtos(
    ParticipantCardsDto dealerCardsDto,
    List<ParticipantCardsDto> playerCardsDtos
) {
    private static final int DEALER_VISIBLE_CARD_SIZE = 1;

    public static InitialDealDtos of(Dealer dealer, List<Player> players) {
        ParticipantCardsDto dealerCardsDto = ParticipantCardsDto.of(
            dealer, DEALER_VISIBLE_CARD_SIZE);

        List<ParticipantCardsDto> playerCardsDto = players.stream()
            .map(ParticipantCardsDto::from)
            .toList();
        return new InitialDealDtos(dealerCardsDto, playerCardsDto);
    }
}
