package domain.game;

import domain.card.CardDeck;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Players {

    public static final int MAX_PLAYER_COUNT = 5;

    private final List<Player> players;

    public Players(List<String> playerNames, List<Integer> playerBettingAmount) {
        validatePlayerCount(playerNames);
        validateDuplicatePlayerName(playerNames);
        this.players = IntStream.range(0, playerNames.size())
                .mapToObj(index -> new Player(playerNames.get(index), playerBettingAmount.get(index)))
                .toList();
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어 수는 최소 1명, 최대" + MAX_PLAYER_COUNT + "명입니다.");
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
