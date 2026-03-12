package domain.result;

import static config.BlackjackGameConstant.DEALER_DISPLAY_NAME;

import domain.betiing.BettingProfit;
import domain.participant.Dealer;
import domain.participant.ParticipantName;
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

    public static BettingResult analyzeDealerBettingResult( List<BettingResult> playerBettingResults) {
        double sum = playerBettingResults.stream()
                .mapToDouble(BettingResult::getProfit)
                .sum();

        double dealerProfit = negate(sum);
        return BettingResult.of(ParticipantName.from(DEALER_DISPLAY_NAME), BettingProfit.from(dealerProfit));
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

    private static double negate(double value) {
        return -value;
    }
}
