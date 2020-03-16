package domain.result;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    public static DealerResult calculateDealerResult(Dealer dealer, Players players) {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();
        Arrays.stream(Result.values())
            .forEach(result -> dealerResult.put(result, 0));

        for (Player player : players.getPlayers()) {
            Result playerResult = calculateDealerResult(dealer, player);
            dealerResult.replace(playerResult, dealerResult.get(playerResult) + 1);
        }
        return new DealerResult(dealerResult);
    }

    private static Result calculateDealerResult(Dealer dealer, Player player) {
        if (calculatePlayerResult(dealer, player).equals(Result.WIN)) {
            return Result.LOSE;
        }
        if (calculatePlayerResult(dealer, player).equals(Result.LOSE)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public static Result calculatePlayerResult(Dealer dealer, Player player) {
        if (isDraw(dealer, player)) {
            return Result.DRAW;
        }
        if (isPlayerLose(dealer, player)) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    private static boolean isDraw(Dealer dealer, Player player) {
        boolean isBothOverBlackJack = dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && player.isLargerThan(Cards.BLACKJACK_SCORE);

        return isBothOverBlackJack || dealer.isScoreSame(player.getTotalScore());
    }

    private static boolean isPlayerLose(Dealer dealer, Player player) {
        /*
         * 딜러는 파산이 아닐 때
         * 플레이어가 파산했거나, 딜러가 플레이어보다 점수가 높으면 짐.
         * */
        return !dealer.isLargerThan(Cards.BLACKJACK_SCORE)
            && (player.isLargerThan(Cards.BLACKJACK_SCORE)
            || dealer.isLargerThan(player.getTotalScore()));
    }

}
