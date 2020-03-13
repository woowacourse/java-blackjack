package blackjack.utils;

import blackjack.domain.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultHandler {

    public static final String COLON = ": ";
    public static final String NEW_LINE = "\n";
    public static final String WINS = "승 ";
    public static final String DRAWS = "무 ";
    public static final String LOSES = "패";

    public static String findAllWinners(Users users) {
        Dealer dealer = users.getDealer();
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        users.getPlayer()
                .forEach(player -> totalResult.put(player, player.compareScore(dealer)));
        return parseAllWinners(totalResult);
    }

    private static Map<Result, Integer> calculatePlayerResultCount(Map<Player, Result> totalResult) {
        Map<Result, Integer> playerResult = new HashMap<>();
        playerResult.put(Result.WIN, 0);
        playerResult.put(Result.DRAW, 0);
        playerResult.put(Result.LOSE, 0);

        for (Result r : totalResult.values()) {
            playerResult.put(r, playerResult.get(r) + 1);
        }
        return playerResult;
    }

    private static String parseAllWinners(Map<Player, Result> totalResult) {
        Map<Result, Integer> dealerResult = calculatePlayerResultCount(totalResult);
        StringBuilder sb = new StringBuilder();

        sb.append(parseDealerTotalResult(dealerResult));
        totalResult.forEach((player, result) ->
                sb.append(player.getName()).append(COLON).append(result.getName()).append(NEW_LINE));
        return sb.toString();
    }

    private static String parseDealerTotalResult(Map<Result, Integer> playerResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(Dealer.DEALER + COLON);
        if (playerResult.get(Result.LOSE) > 0) {
            sb.append(playerResult.get(Result.LOSE)).append(WINS);
        }
        if (playerResult.get(Result.DRAW) > 0) {
            sb.append(playerResult.get(Result.DRAW)).append(DRAWS);
        }
        if (playerResult.get(Result.WIN) > 0) {
            sb.append(playerResult.get(Result.WIN)).append(LOSES);
        }
        sb.append(NEW_LINE);
        return sb.toString();
    }
}
