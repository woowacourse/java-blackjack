package blackjack.domain.user;

import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.user.component.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String MAX_PLAYER_SIZE_ERR_MSG = "플레이어는 최대 %명입니다.";
    public static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players (Map<Name, BettingAmount> playersInfo) {
        Objects.requireNonNull(playersInfo);
        if (playersInfo.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(String.format(MAX_PLAYER_SIZE_ERR_MSG, MAX_PLAYER));
        }

        players = new ArrayList<>();
        for (Map.Entry<Name, BettingAmount> entry : playersInfo.entrySet()) {
            Objects.requireNonNull(entry.getKey());
            Objects.requireNonNull(entry.getValue());

            players.add(new Player(entry.getKey(), entry.getValue()));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(x -> x.getName().toString())
                .collect(Collectors.toList());
    }
}
