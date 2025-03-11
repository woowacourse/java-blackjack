package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    private final Map<PlayerName, Player> players;

    public Players(List<PlayerName> playerNames) {
        this.players = playerNames.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        Player::new,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    public void giveCard(PlayerName playerName, Cards cards) {
        Player selectedPlayer = selectPlayer(playerName);
        selectedPlayer.addCard(cards);
    }

    public boolean isDrawable(PlayerName playerName) {
        Player player = selectPlayer(playerName);
        return player.isDrawable();
    }

    private Player selectPlayer(PlayerName playerName) {
        validatePlayerExist(playerName);
        return players.get(playerName);
    }

    private void validatePlayerExist(PlayerName playerName) {
        if (!players.containsKey(playerName)) {
            throw new IllegalArgumentException("플레이어가 존재하지 않습니다.");
        }
    }

    public Cards getPlayerCard(PlayerName playerName) {
        Player selectedPlayer = selectPlayer(playerName);
        return selectedPlayer.getCards();
    }

    public GameStatistics calculateGameStatistics(Dealer dealer) {
        Map<PlayerName, GameResult> gameResults = players.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().decideGameResult(dealer)
                ));

        return new GameStatistics(gameResults);
    }

    public Map<PlayerName, Player> getPlayersInfo() {
        return Collections.unmodifiableMap(players);
    }
}
