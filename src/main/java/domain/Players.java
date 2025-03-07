package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    public static final int MAX_PLAYER_COUNT = 5;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicatePlayerName(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }


    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 최소 플레이어 수는 1명입니다.");
        }
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 플레이어 수는 " + MAX_PLAYER_COUNT + "명입니다.");
        }
    }

    private void validateDuplicatePlayerName(List<String> playerNames) {
        Set<String> uniquePlayerNames = new HashSet<>(playerNames);
        if (uniquePlayerNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 플레이어 이름입니다.");
        }
    }

    public void drawCardWhenStart(CardDeck cardDeck) {
        players.forEach(player -> player.drawCardWhenStart(cardDeck));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getAllPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
