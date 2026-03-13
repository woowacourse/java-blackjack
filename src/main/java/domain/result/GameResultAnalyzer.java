package domain.result;

import static config.BlackjackGameConstant.DEALER_DISPLAY_NAME;

import domain.betiing.BettingProfit;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.Dealer;
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
        int playerScore = player.getResultScore();
        int dealerScore = dealer.getResultScore();

        if (playerScore < dealerScore) {
            return WinningStatus.LOSS;
        }

        if (playerScore > dealerScore) {
            return WinningStatus.WIN;
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
        double sum = playerBettingResults.stream()
                .mapToDouble(BettingResult::getProfit)
                .sum();

        double dealerProfit = negate(sum);
        return BettingResult.of(ParticipantName.from(DEALER_DISPLAY_NAME), BettingProfit.from(dealerProfit));
    }

    private static double negate(double value) {
        return -value;
    }
}
