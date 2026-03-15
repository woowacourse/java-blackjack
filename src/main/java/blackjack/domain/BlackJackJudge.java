package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackJudge {


    public FinalIncome judge(Players players, Dealer dealer) {
        Map<Player, Integer> incomeResult = new LinkedHashMap<>();
        int dealerIncome = 0;

        for (Player player : players.getPlayers()) {
            GameResult gameResult = judgeGameResult(player, dealer);
            int income = gameResult.calculateIncome(player.getBetAmount());  // ← 위임

            incomeResult.put(player, income);
            dealerIncome -= income;
        }

        return new FinalIncome(dealerIncome, incomeResult);
    }

    private GameResult judgeGameResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return GameResult.TIE;
        }
        if (player.isBlackJack()) {
            return GameResult.BACKJACK_WIN;
        }

        int playerTotalPoint = player.getTotalPoint();
        int dealerTotalPoint = dealer.getTotalPoint();

        if (playerTotalPoint == dealerTotalPoint) {
            return GameResult.TIE;
        }
        if (playerTotalPoint > dealerTotalPoint) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
