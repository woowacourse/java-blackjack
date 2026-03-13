package domain.result;

import domain.participant.Player;
import domain.participant.Dealer;
import domain.participant.Players;

import java.util.List;

public class WinningResultAnalyzer {

    private WinningResultAnalyzer() {
    }

    public static List<PlayerWinningResult> analyzePlayerResults(Players players, Dealer dealer) {
        return players.stream()
                .map(player -> {
                    WinningStatus winningStatus = judgePlayerGameResult(dealer, player);
                    return new PlayerWinningResult(player.getName(), winningStatus);
                })
                .toList();
    }

    private static WinningStatus judgePlayerGameResult(Dealer dealer, Player player) {
        if(dealer.isBusted()) return player.isBusted() ? WinningStatus.DRAW : WinningStatus.WIN;

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

}
