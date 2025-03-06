package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> userName) {
        this.players = userName.stream()
                .map(Player::new)
                .toList();
    }

    public void giveCard(String username, List<Card> cards) {
        Player selectedPlayer = selectPlayer(username);
        selectedPlayer.addCard(cards);
    }

    private Player selectPlayer(String username) {
        return players.stream().filter(player -> player.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어는 존재하지 않습니다."));
    }

    public List<Card> getPlayerCard(String username) {
        Player selectedPlayer = selectPlayer(username);

        return selectedPlayer.getCards();
    }

    public boolean canGetMoreCard(String username) {
        Player player = selectPlayer(username);
        return player.canGetMoreCard();
    }

    public List<String> getUsernames() {
        return players.stream()
                .map(Player::getUsername)
                .toList();
    }

    public Map<String, Gamer> getPlayersInfo() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getUsername,
                        Gamer::clone,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public GameStatistics calculateGameStatistics(Dealer dealer) {
        Map<String, GameResult> gameResults = players.stream()
                .collect(Collectors.toMap(
                        Player::getUsername,
                        player -> player.decideGameResult(dealer),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));

        return new GameStatistics(gameResults);
    }
}
