package domain.game;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;

import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MAX_PLAYER_COUNT = 5;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicatePlayerName(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
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
