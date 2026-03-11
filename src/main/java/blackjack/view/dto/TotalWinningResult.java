package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameResult;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public record TotalWinningResult(
        long dealerWinCount,
        long dealerLossCount,
        List<PlayerGameResult> playerResults
) {
    
    public static TotalWinningResult of(Dealer dealer, List<Player> players) {
        Map<GameResult, Long> dealerResult = dealer.determineGameResult(players);
        long dealerWinCount = dealerResult.getOrDefault(GameResult.WIN, 0L);
        long dealerLoseCount = dealerResult.getOrDefault(GameResult.LOSE, 0L);
        
        List<PlayerGameResult> playerResults = players.stream()
                .map(player -> PlayerGameResult.of(player, dealer))
                .toList();
        
        return new TotalWinningResult(dealerWinCount, dealerLoseCount, playerResults);
    }
}
