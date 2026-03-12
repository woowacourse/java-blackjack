package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackJudge {
    private static final int BLACKJACK_POINT = 21;


    public FinalIncome judge(Players players, Dealer dealer) {
        Map<Player, Integer> incomeResult = new LinkedHashMap<>();
        int dealerIncome = 0;

        for (Player player : players.getPlayers()) {
            GameResult gameResult = judgeGameResult(player, dealer);
            int income = calculateIncome(player, gameResult);

            incomeResult.put(player, income);
            dealerIncome -= income;
        }

        return new FinalIncome(dealerIncome, incomeResult);
    }

    private int calculateIncome(Player player, GameResult gameResult) {
        if (gameResult == GameResult.BACKJACK_WIN) {
            return (int) (1.5 * player.getBetAmount());
        }
        if (gameResult == GameResult.WIN) {
            return player.getBetAmount();
        }
        if (gameResult == GameResult.LOSE) {
            return -player.getBetAmount();
        }
        return 0;
    }

    private GameResult judgeGameResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        int playerTotalPoint = player.getTotalPoint();
        int dealerTotalPoint = dealer.getTotalPoint();

        if (playerTotalPoint == dealerTotalPoint) {
            return GameResult.TIE;
        }

        if (playerTotalPoint == BLACKJACK_POINT) {
            return GameResult.BACKJACK_WIN;
        }

        if (playerTotalPoint > dealerTotalPoint) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }

}
