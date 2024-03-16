package blackjack.domain.player;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Hands;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Players {

    private final List<User> players;

    private Players(final List<User> players) {
        this.players = players;
    }

    public static Players of(final PlayerNames playerNames, final Predicate<UserName> userWantToHit) {
        final List<User> players = new ArrayList<>();
        playerNames.getNames().forEach(name -> players.add(Player.of(name, userWantToHit)));

        return new Players(players);
    }

    public Map<UserName, Hands> getPlayersOpendHands() {
        return players.stream()
                .collect(toMap(User::getUserName, User::getOpenedHands, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public List<User> getPlayers() {
        return players;
    }
}
