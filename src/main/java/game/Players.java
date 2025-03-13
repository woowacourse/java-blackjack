package game;

import static card.CardDeck.DRAW_COUNT_WHEN_START;

import card.CardDeck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MAX_PLAYER_COUNT = 5;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatePlayerName(players);
        this.players = players;
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어 수는 최소 1명, 최대" + MAX_PLAYER_COUNT + "명입니다.");
        }
    }

    private void validateDuplicatePlayerName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>(players);
        if (uniquePlayers.size() != players.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 플레이어 이름입니다.");
        }
    }

    public List<GameResult> judgeGameResult(Dealer dealer) {
        List<GameResult> gameResults = new ArrayList<>();
        for (Player player : players) {
            gameResults.add(GameResult.of(dealer, player));
        }
        return gameResults;
    }

    public void drawCard(CardDeck cardDeck) {
        players.forEach(player -> player.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_START)));
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
