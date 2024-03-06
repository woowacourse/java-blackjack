package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> values;

    public Players(final List<Player> players) {
        this.values = players;
    }

    public Map<Name, WinStatus> determineWinStatus(final Dealer dealer) {
        final Score dealerScore = dealer.calculate();

        Map<Name, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : values) {
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScore, player.calculate()));
        }
        return playersWinStatus;
    }
}
