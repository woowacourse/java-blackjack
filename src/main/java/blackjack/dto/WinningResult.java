package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record WinningResult(
        Map<String, Boolean> winningResult
) {

    public static WinningResult from(Players players, Dealer dealer) {
        Map<String, Boolean> winningResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            winningResult.put(player.getName(), player.winsAgainst(dealer));
        }
        return new WinningResult(winningResult);
    }

//    public int getProfitOfDealer() {
//        return -winningResult.values().stream()
//                .mapToInt(integer -> integer)
//                .sum();
//    }

}
