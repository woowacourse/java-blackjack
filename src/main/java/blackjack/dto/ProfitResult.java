package blackjack.dto;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.Bettings;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.state.State;
import java.util.LinkedHashMap;
import java.util.Map;

public record ProfitResult(
        Map<String, Integer> profitResult
) {

    public static ProfitResult from(Players players, StateResult stateResult, Bettings bettings) {
        Map<String, Integer> profitResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            BettingAmount bettingAmount = bettings.findByPlayer(player);
            State state = stateResult.get(player);
            profitResult.put(player.getName(), (int) state.apply(bettingAmount.amount()));

        }
        return new ProfitResult(profitResult);
    }

    public int getProfitOfDealer() {
        return profitResult.values().stream()
                .mapToInt(Integer::intValue)
                .sum() * (-1);
    }

}
