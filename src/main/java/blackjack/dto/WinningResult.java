package blackjack.dto;

import blackjack.domain.betting.BettingRepository;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record WinningResult(
        Map<String, Integer> winningResult
) {

    public static WinningResult from(Players players, Dealer dealer, BettingRepository bettingRepository) {
        Map<String, Integer> winningResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            if (player.winsAgainst(dealer)) {
                winningResult.put(player.getName(), bettingRepository.findByPlayer(player).amount());
            }
        }
        return new WinningResult(winningResult);
    }

    public int getProfitOfDealer() {
        return -winningResult.values().stream()
                .mapToInt(integer -> integer)
                .sum();
    }

}
