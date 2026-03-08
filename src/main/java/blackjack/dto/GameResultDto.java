package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.Players;

import java.util.List;

public record GameResultDto(
        List<PlayerScoreDto> playerResults,
        DealerScoreDto dealerResult
) {
    public static GameResultDto from(Players players, Dealer dealer) {
        List<PlayerScoreDto> playerResults = players.getPlayers().stream()
                .map(PlayerScoreDto::from)
                .toList();

        DealerScoreDto dealerResult = DealerScoreDto.from(dealer);
        return new GameResultDto(playerResults, dealerResult);
    }
}