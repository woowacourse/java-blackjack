package blackjack.dto;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.Bettings;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record ProfitResult(
        Map<String, Integer> profitResult
) {

    public static ProfitResult from(Bettings bettings, Players players, Dealer dealer) {
        Map<String, Integer> profitResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            BettingAmount bettingAmount = bettings.findByPlayer(player);
            if (player.winsAgainst(dealer)) {
                profitResult.put(player.getName(), bettingAmount.amount());
            } else {
                profitResult.put(player.getName(), -bettingAmount.amount());
            }
        }
        return new ProfitResult(profitResult);
    }

    public int getProfitOfDealer() {
        return -profitResult.values().stream()
                .mapToInt(integer -> integer)
                .sum();
    }

}
