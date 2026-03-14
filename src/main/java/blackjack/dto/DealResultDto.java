package blackjack.dto;

import blackjack.domain.BlackjackGame;
import java.util.List;

public record DealResultDto(
        List<PlayerHandDto> playerHands,
        CardDto dealerOpenCard
) {
    public static DealResultDto from(BlackjackGame game) {
        List<PlayerHandDto> playerHands = game.getPlayers().getPlayers().stream()
                .map(PlayerHandDto::from)
                .toList();
        CardDto dealerCard = CardDto.from(game.getDealer().getCards().getFirst());
        return new DealResultDto(playerHands, dealerCard);
    }
}

