package blackjack.domain;

import blackjack.dto.PlayerInfo;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> values;

    public Players(final List<Player> values) {
        validateDuplicationPlayers(values);
        this.values = values;
    }

    private void validateDuplicationPlayers(final List<Player> players) {
        final int distinctCount = (int) players.stream()
                .map(player -> player.getName())
                .distinct()
                .count();
        if (distinctCount != players.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    public List<PlayerInfo> getInitPlayerInfo() {
        return values.stream()
                .map(PlayerInfo::playerToInfo)
                .collect(Collectors.toList());
    }
}
