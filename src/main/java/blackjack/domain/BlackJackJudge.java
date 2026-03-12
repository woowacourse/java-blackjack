package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackJudge {
    private static final int BLACKJACK_POINT = 21;


    public FinalIncome judge(Players players, Dealer dealer) {
        Map<Player, GameResult> result = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            result.put(player, judgeGameResult(player, dealer));
        }

        int dealerIncome = 0;

        Map<Player, Integer> incomeResult = new LinkedHashMap<>();
        for (Player player : result.keySet()) {
            if (result.get(player) == GameResult.BACKJACK_WIN) {
                int income = (int) (1.5 * player.getBetAmount());
                incomeResult.put(player, income);
                dealerIncome -= income;
            } else if (result.get(player) == GameResult.WIN) {
                int income = player.getBetAmount();
                incomeResult.put(player, income);
                dealerIncome -= income;
            } else if (result.get(player) == GameResult.LOSE) {
                int income = -player.getBetAmount();
                incomeResult.put(player, income);
                dealerIncome += player.getBetAmount();
            }
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
