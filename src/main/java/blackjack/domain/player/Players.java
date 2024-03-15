package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Hands;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final PlayerNames playerNames) {
        return new Players(playerNames.getNames().stream().map(Player::new).toList());
    }

    public boolean hasStandPlayer() {
        return players.stream().anyMatch(User::isStand);
    }

    public Map<UserName, Hands> getPlayersOpendHands() {
        return players.stream()
                .collect(toMap(User::getPlayerName, User::getOpenedHands, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public List<User> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
