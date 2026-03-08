package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import java.util.List;

public record GameResult(
        List<PlayerScoreResult> playerResults,
        DealerScoreResult dealerResult
) {
    public static GameResult from(Players players, Dealer dealer) {
        List<PlayerScoreResult> playerResults = players.getPlayers().stream()
                .map(PlayerScoreResult::from)
                .toList();

        DealerScoreResult dealerResult = DealerScoreResult.from(dealer);
        return new GameResult(playerResults, dealerResult);
    }
}