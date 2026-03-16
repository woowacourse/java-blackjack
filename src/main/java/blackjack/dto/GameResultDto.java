package blackjack.dto;

import blackjack.domain.BlackjackGame;

import java.util.List;

public record GameResultDto(
        List<PlayerScoreDto> playerResults,
        DealerScoreDto dealerResult
) {
    public static GameResultDto from(BlackjackGame game) {
        List<PlayerScoreDto> playerResults = game.getPlayers().getPlayers().stream()
                .map(PlayerScoreDto::from)
                .toList();

        DealerScoreDto dealerResult = DealerScoreDto.from(game.getDealer());
        return new GameResultDto(playerResults, dealerResult);
    }
}