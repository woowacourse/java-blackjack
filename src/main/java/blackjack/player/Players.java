package blackjack.player;

import blackjack.card.Deck;
import java.util.HashSet;
import java.util.List;

public class Players {

    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 10;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validatePlayers(List<String> playerNames) {
        validateNotNull(playerNames);
        validateSize(playerNames);
        validateUniqueNames(playerNames);
    }

    private void validateNotNull(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("플레이어로 null이 전달되었습니다.");
        }
    }

    private void validateSize(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYER_COUNT || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(
                    "플레이어의 수는 " + MIN_PLAYER_COUNT + "명 이상 " + MAX_PLAYER_COUNT + "명 이하여야 합니다."
            );
        }
    }

    private void validateUniqueNames(List<String> playerNames) {
        if (new HashSet<>(playerNames).size() != playerNames.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    public void doInitialDraw(Deck deck) {
        players.forEach(player -> player.doInitialDraw(deck));
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
