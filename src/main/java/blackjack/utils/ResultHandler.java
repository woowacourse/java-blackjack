package blackjack.utils;

import blackjack.domain.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

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

    private static String parseAllWinners(Map<Player, Result> totalResult) {
        int dealerWins = 0, dealerLoses = 0, dealerDraws = 0;
        StringBuilder sb = new StringBuilder();
        for (Result r : totalResult.values()) {
            if (r.equals(Result.WIN)) {
                dealerLoses++;
                continue;
            }
            if (r.equals(Result.LOSE)) {
                dealerWins++;
                continue;
            }
            if (r.equals(Result.DRAW)) {
                dealerDraws++;
            }
        }
        sb.append(Dealer.DEALER + ": ");
        if (dealerWins > 0) {
            sb.append(dealerWins).append("승 ");
        }
        if (dealerDraws > 0) {
            sb.append(dealerDraws).append("무 ");
        }
        if (dealerLoses > 0) {
            sb.append(dealerLoses).append("패");
        }
        sb.append("\n");

        totalResult.forEach((player, result) ->
                sb.append(player.getName()).append(": ").append(result.getName()).append("\n"));

        return sb.toString();
    }
}
