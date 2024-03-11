package blackjack.domain.participants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinOrLose {
    private final Map<Player, Boolean> winOrLose;

    private WinOrLose(Map<Player, Boolean> winOrLose) {
        this.winOrLose = winOrLose;
    }

    public static WinOrLose calculateWinOrLose(List<Player> players, int dealerScore) {
        Map<Player, Boolean> winOrLose = new LinkedHashMap<>();
        players.forEach(player -> winOrLose.put(player, player.isWin(dealerScore)));
        return new WinOrLose(winOrLose);
    }

    public int countDealerWin() {
        return (int) winOrLose.values().stream()
                .filter(isWin -> !isWin)
                .count();
    }

    public int size() {
        return winOrLose.size();
    }

    public Map<Player, Boolean> getWinOrLose() {
        return new HashMap<>(winOrLose);
    }

    public Boolean getResult(Player player) {
        return winOrLose.get(player);
    }
}
