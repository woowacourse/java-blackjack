package blackjack.utils;

import blackjack.domain.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultHandler {
    public static String findAllWinners(Users users) {
        Dealer dealer = users.getDealer();
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        users.getPlayer()
                .forEach(player -> totalResult.put(player, player.compareScore(dealer)));
        return parseAllWinners(totalResult);
    }

    private static Map<Result, Integer> calculateDealerResultCount(Map<Player, Result> totalResult) {
        Map<Result, Integer> dealerResult = new HashMap<>();
        dealerResult.put(Result.WIN, 0);
        dealerResult.put(Result.DRAW, 0);
        dealerResult.put(Result.LOSE, 0);

        for (Result r : totalResult.values()) {
            dealerResult.put(r, dealerResult.get(r) + 1);
        }
        return dealerResult;
    }

    private static String parseAllWinners(Map<Player, Result> totalResult) {
        Map<Result, Integer> dealerResult = calculateDealerResultCount(totalResult);
        StringBuilder sb = new StringBuilder();

        sb.append(parseDealerTotalResult(dealerResult));
        totalResult.forEach((player, result) ->
                sb.append(player.getName()).append(": ").append(result.getName()).append("\n"));
        return sb.toString();
    }

    private static String parseDealerTotalResult(Map<Result, Integer> dealerResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(Dealer.DEALER + ": ");
        if (dealerResult.get(Result.WIN) > 0) {
            sb.append(dealerResult.get(Result.WIN)).append("승 ");
        }
        if (dealerResult.get(Result.DRAW) > 0) {
            sb.append(dealerResult.get(Result.DRAW)).append("무 ");
        }
        if (dealerResult.get(Result.LOSE) > 0) {
            sb.append(dealerResult.get(Result.LOSE)).append("패");
        }
        sb.append("\n");
        return sb.toString();
    }
}
