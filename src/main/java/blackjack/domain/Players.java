package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private final List<Player> values;

    public Players(final List<Player> players) {
        validateDuplicate(players);
        this.values = players;
    }

    private void validateDuplicate(final List<Player> players) {
        if (Set.copyOf(players).size() != players.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public Map<Name, WinStatus> determineWinStatus(final Score dealerScore) {
        Map<Name, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : values) {
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScore, player.calculate()));
        }
        return playersWinStatus;
    }
}
