package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import java.util.List;

public record GameResultDtos(
    DealerResultDto dealerResultDto,
    List<GameResultDto> gameResultDtos
) {
    public static GameResultDtos of(Dealer dealer, List<Player> players) {
        List<GameResultDto> gameResultDtos = players.stream()
            .map(player -> convertFrom(dealer, player))
            .toList();

        DealerResultDto dealerResultDto = DealerResultDto.from(gameResultDtos);
        return new GameResultDtos(dealerResultDto, gameResultDtos);
    }

    private static GameResultDto convertFrom(Dealer dealer, Player player) {
        GameResult result = dealer.judgeAgainst(player);
        return GameResultDto.of(player, result);
    }
}
