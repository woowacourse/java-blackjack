package blackjack.domain.user;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Hands;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class Players {

    private final Map<UserName, Player> players;

    public Players(PlayerNames playerNames) {
        this.players = playerNames.getNames().stream()
                .collect(toMap(Function.identity(),
                        Player::new,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Player find(UserName name) {
        return players.get(name);
    }


    public boolean isAllBust() {
        return players.values().stream()
                .allMatch(player -> player.getHands().calculateScore().isBust());
    }

    public Map<UserName, Hands> getHands() {
        return players.entrySet().stream()
                .collect(toMap(Entry::getKey,
                        entry -> entry.getValue().getHands(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<UserName> getPlayerNames() {
        return players.keySet().stream().toList();
    }

    public List<Player> getPlayer() {
        return players.values().stream().toList();
    }
}
