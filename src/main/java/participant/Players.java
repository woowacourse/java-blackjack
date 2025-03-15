package participant;

import constant.WinningResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Map<Player, WinningResult> results = new LinkedHashMap<>();
        for (Player player : players) {
            WinningResult winningResult = player.compareTo(dealerScore);
            results.put(player, winningResult);
            
        }
        return results;
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
