package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.IntStream;

public record TotalWinningResult(
        int dealerProfit,
        List<PlayerProfit> playerResults
) {
    
    public static TotalWinningResult of(Dealer dealer, List<Player> players) {
        List<Integer> playersProfit = dealer.determinePlayersProfit(players);
        int dealerProfit = dealer.determineProfit(players);
        
        List<PlayerProfit> playerResults = IntStream.range(0, players.size())
                .mapToObj(index -> PlayerProfit.of(players.get(index), playersProfit.get(index)))
                .toList();
        
        return new TotalWinningResult(dealerProfit, playerResults);
    }
}
