package domain.result;

import domain.betiing.BettingProfit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class BettingResultAnalyzer {

    private BettingResultAnalyzer() {
    }

    public static List<BettingResult> analyze(Players players, Dealer dealer) {
        return players.stream().map(player -> {
                    GameResult gameResult = judgeGameResult(dealer, player);
                    return BettingResult.of(player.getName(), BettingProfit.of(gameResult, player.getBetAmount()));
                }
        ).toList();
    }

    private static GameResult judgeGameResult(Dealer dealer, Player player) {
        if(dealer.isBusted()) return player.isBusted() ? GameResult.DRAW : GameResult.WIN;

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }

        if (player.isBlackjack()) {
            return GameResult.BLCAKJACK;
        }

        if (dealer.isBlackjack()) {
            return GameResult.LOSS;
        }

        return judgeByScore(dealer.getResultScore(), player.getResultScore());
    }

    private static GameResult judgeByScore(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return GameResult.LOSS;
        }

        if (dealerScore == playerScore) {
            return GameResult.DRAW;
        }

        return GameResult.WIN;
    }
}
