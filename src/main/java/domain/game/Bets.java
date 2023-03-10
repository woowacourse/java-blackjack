package domain.game;

import domain.participant.Player;
import domain.result.WinningResult;
import domain.result.WinningStatus;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final HashMap<Player, Money> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final Money money) {
        bets.put(player, money);
    }

    public Map<Player, Money> calculateBets(WinningResult winningResult) {
        Map<Player, Money> weightedBets = new LinkedHashMap<>();
        Map<Player, WinningStatus> winningStatusMap = winningResult.getPlayersResult();
        for (Player player : winningStatusMap.keySet()) {
            BigDecimal weight = player.profitRate();
            BigDecimal statusMultiplier = winningStatusMap.get(player).multiplier();
            Money weightedBet = this.bets.get(player)
                    .multiply(weight)
                    .multiply(statusMultiplier);
            weightedBets.put(player, weightedBet);
        }
        return weightedBets;
    }
}
