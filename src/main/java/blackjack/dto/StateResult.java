package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.state.State;
import java.util.LinkedHashMap;
import java.util.Map;

public record StateResult(
        Map<Player, State> stateResult
) {

    public static StateResult from(Players players, Dealer dealer) {
        Map<Player, State> stateResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            stateResult.put(player, State.from(player, dealer));
        }
        return new StateResult(stateResult);
    }

    public State get(Player player) {
        return stateResult.get(player);
    }

//    public int getProfitOfDealer() {
//        return -stateResult.values().stream()
//                .mapToInt(integer -> integer)
//                .sum();
//    }

}
