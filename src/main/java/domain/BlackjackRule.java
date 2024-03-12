package domain;

import dto.DealerResult;
import dto.GameResult;
import dto.PlayerResult;
import dto.WinLose;

import java.util.List;

public class BlackjackRule {
    public GameResult calculate(final Players players, final Dealer dealer) {
        final List<PlayerResult> playerResults = calculatePlayerResults(players, dealer);
        final DealerResult dealerResult = calculateDealerResult(players, dealer, countPlayerWins(playerResults));

        return new GameResult(playerResults, dealerResult);
    }

    private DealerResult calculateDealerResult(final Players players, final Dealer dealer, final long playerWins) {
        return new DealerResult(dealer.name(), (int) (players.size() - playerWins), (int) playerWins);
    }

    private List<PlayerResult> calculatePlayerResults(final Players players, final Dealer dealer) {
        return players.getPlayers().stream()
                .map(player -> match(player, dealer))
                .toList();
    }

    private long countPlayerWins(final List<PlayerResult> playerResults) {
        return playerResults.stream()
                .filter(playerResult -> playerResult.winLose() == WinLose.WIN)
                .count();
    }

    private PlayerResult match(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return new PlayerResult(player.name(), WinLose.LOSE);
        }
        if (dealer.isBust()) {
            return new PlayerResult(player.name(), WinLose.WIN);
        }
        if (dealer.score() >= player.score()) {
            return new PlayerResult(player.name(), WinLose.LOSE);
        }
        return new PlayerResult(player.name(), WinLose.WIN);
    }
}
