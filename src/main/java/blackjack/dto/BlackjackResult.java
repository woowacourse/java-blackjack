package blackjack.dto;

import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import java.util.List;

public record BlackjackResult(String dealerProfit, List<PlayerProfitResult> playerProfits) {
    public static BlackjackResult of(Dealer dealer, Players players) {
        List<PlayerProfitResult> playerProfits = players.getPlayers().stream()
                .map(PlayerProfitResult::of)
                .toList();

        return new BlackjackResult(dealer.getProfit(), playerProfits);
    }
}
