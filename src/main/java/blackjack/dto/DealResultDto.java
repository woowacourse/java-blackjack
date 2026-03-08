package blackjack.dto.response;

import blackjack.domain.Dealer;
import blackjack.domain.Players;

import java.util.List;

public record DealResultDto(
        List<PlayerHandDto> playerHands,
        CardDto dealerOpenCard
) {
    public static DealResultDto from(Players players, Dealer dealer) {
        List<PlayerHandDto> playerHands = players.getPlayers().stream()
                .map(PlayerHandDto::from)
                .toList();
        CardDto dealerCard = CardDto.from(dealer.getOpenCard());
        return new DealResultDto(playerHands, dealerCard);
    }
}
