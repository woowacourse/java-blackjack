package participant;

import constant.WinningResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players registerPlayers(List<String> names, Dealer dealer) {
        return new Players(names.stream()
                .map(Nickname::new)
                .map(nickname -> new Player(nickname, dealer.drawInitialCards()))
                .toList());
    }

    public Map<Player, WinningResult> deriveResults(int dealerScore) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.compareTo(dealerScore),
                        (x, y) -> y, LinkedHashMap::new
                ));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getNickname)
                .toList();
    }
}
