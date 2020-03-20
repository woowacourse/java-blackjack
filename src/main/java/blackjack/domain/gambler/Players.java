package blackjack.domain.gambler;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Players {

    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE =
        String.format("참여 인원은 %d명 이상, %d명 이하이어야 합니다.", MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);

    private final List<Player> players;

    public Players(Map<Name, BettingMoney> playerInfo) {
        validatePlayerInfo(playerInfo);
        this.players = Collections.unmodifiableList(playerInfo.keySet().stream()
            .map(playerName -> new Player(playerName, playerInfo.get(playerName)))
            .collect(Collectors.toList()));
    }

    private void validatePlayerInfo(Map<Name, BettingMoney> playerInfo) {
        if (Objects.isNull(playerInfo)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
        if (playerInfo.size() < MINIMUM_PLAYER_COUNT || playerInfo.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
            .map(Player::getName)
            .map(Name::toString)
            .collect(Collectors.toList()));
    }
}
