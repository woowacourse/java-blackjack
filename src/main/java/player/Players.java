package player;

import card.Deck;

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
            throw new IllegalArgumentException("[ERROR] 플레이어로 null이 전달되었습니다.");
        }
    }

    private void validateSize(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYER_COUNT || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 1명 이상 10명 이하여야 합니다.");
        }
    }

    private void validateUniqueNames(List<String> playerNames) {
        int distinctNameCount = (int) playerNames.stream()
                .distinct()
                .count();
        if (distinctNameCount != playerNames.size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    public void initDrawCards(Deck deck) {
        players.forEach(player -> player.initDrawCards(deck));
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
