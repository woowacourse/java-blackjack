package domain.result;

import static config.BlackjackGameConstant.DEALER_DISPLAY_NAME;

import domain.betiing.BettingProfit;
import domain.participant.Dealer;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class GameResultAnalyzer {

    private GameResultAnalyzer() {
    }

    public static List<PlayerWinningResult> analyzePlayerWinningResults(Players players, Dealer dealer) {
        return players.stream()
                .map(player -> {
                    WinningStatus winningStatus = judgePlayerGameResult(dealer, player);
                    return new PlayerWinningResult(player.getName(), winningStatus);
                })
                .toList();
    }

    private static WinningStatus judgePlayerGameResult(Dealer dealer, Player player) {
        if (dealer.isBusted()) {
            return player.isBusted() ? WinningStatus.DRAW : WinningStatus.WIN;
        }

        if (player.isBusted()) {
            return WinningStatus.LOSS;
        }

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return WinningStatus.DRAW;
        }

        if (player.isBlackjack()) {
            return WinningStatus.BLCAKJACK;
        }

        if (dealer.isBlackjack()) {
            return WinningStatus.LOSS;
        }

        return judgePlayerWinningStatusByScore(player, dealer);
    }

    private static WinningStatus judgePlayerWinningStatusByScore(Player player, Dealer dealer) {
        if (player.hasHigherScoreThan(dealer)) {
            return WinningStatus.WIN;
        }

        if (dealer.hasHigherScoreThan(player)) {
            return WinningStatus.LOSS;
        }

        return WinningStatus.DRAW;
    }

    public static List<BettingResult> analyzePlayerBettingResults(Players players, Dealer dealer) {
        return players.stream().map(player -> {
                    WinningStatus winningStatus = judgePlayerGameResult(dealer, player);
                    return BettingResult.of(player.getName(), BettingProfit.of(winningStatus, player.getBetAmount()));
                }
        ).toList();
    }

    public static BettingResult analyzeDealerBettingResult(List<BettingResult> playerBettingResults) {
        long sum = playerBettingResults.stream()
                .mapToLong(BettingResult::getProfit)
                .sum();

        long dealerProfit = negate(sum);
        return BettingResult.of(ParticipantName.from(DEALER_DISPLAY_NAME), BettingProfit.from(dealerProfit));
    }

    private static long negate(long value) {
        return -value;
    }
}
