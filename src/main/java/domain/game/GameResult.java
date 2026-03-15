package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public record GameResult(int dealerProfit, List<PlayerGameResult> playerResults) {
    public static GameResult from(Players players, Dealer dealer) {
        return new GameResult(createPlayerResults(players, dealer));
    }

    private GameResult(List<PlayerGameResult> playerResults) {
        this(calculateDealerProfit(playerResults), playerResults);
    }

    private static List<PlayerGameResult> createPlayerResults(Players players, Dealer dealer) {
        return players.getPlayers().stream()
                .map(player -> createPlayerResult(player, dealer))
                .toList();
    }

    private static PlayerGameResult createPlayerResult(Player player, Dealer dealer) {
        WinningStatus status = WinningStatus.of(player, dealer);
        return new PlayerGameResult(player.name(), player.profit(status));
    }

    private static int calculateDealerProfit(List<PlayerGameResult> playerResults) {
        return -1 * calculatePlayersProfit(playerResults);

    }

    private static int calculatePlayersProfit(List<PlayerGameResult> playerResults) {
        return playerResults.stream()
                .mapToInt(PlayerGameResult::profit)
                .sum();
    }
}
