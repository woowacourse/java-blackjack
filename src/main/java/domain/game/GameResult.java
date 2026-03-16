package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public record GameResult(int dealerProfit, List<PlayerGameResult> playerResults) {

    public static GameResult from(Players players, Dealer dealer) {
        List<PlayerGameResult> playerResults = players.getPlayers().stream()
                .map(player -> createPlayerResult(player, dealer))
                .toList();

        int dealerProfit = -1 * calculatePlayersProfit(playerResults);

        return new GameResult(dealerProfit, playerResults);
    }

    private static PlayerGameResult createPlayerResult(Player player, Dealer dealer) {
        WinningStatus status = WinningStatus.of(player, dealer);
        return new PlayerGameResult(player.name(), player.profit(status));
    }

    private static int calculatePlayersProfit(List<PlayerGameResult> playerResults) {
        return playerResults.stream()
                .mapToInt(PlayerGameResult::profit)
                .sum();
    }
}
