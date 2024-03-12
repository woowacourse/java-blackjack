package domain;

import dto.DealerResult;
import dto.GameResult;
import dto.PlayerResult;
import dto.WinLose;

import java.util.List;

public class BlackjackRule {
    public GameResult calculate(final Players players, final Dealer dealer) {
        final List<PlayerResult> playerResults = players.getPlayers().stream()
                .map(player -> match(player, dealer))
                .toList();

        final long playerWins = countPlayerWins(playerResults);

        final DealerResult dealerResult = new DealerResult(dealer.getName(), (int) (players.size() - playerWins), (int) playerWins);
        return new GameResult(playerResults, dealerResult);
    }

    private long countPlayerWins(final List<PlayerResult> playerResults) {
        return playerResults.stream()
                .filter(playerResult -> playerResult.winLose() == WinLose.WIN)
                .count();
    }

    private PlayerResult match(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return new PlayerResult(player.getName(), WinLose.LOSE);
        }
        if (dealer.isBust()) {
            return new PlayerResult(player.getName(), WinLose.WIN);
        }
        if (dealer.score() >= player.score()) {
            return new PlayerResult(player.getName(), WinLose.LOSE);
        }
        return new PlayerResult(player.getName(), WinLose.WIN);
    }
}
