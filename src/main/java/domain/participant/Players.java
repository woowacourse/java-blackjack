package domain.participant;

import domain.card.CardHand;
import java.util.List;
import java.util.Set;

public class Players {
    private static final int MAX_PLAYER_COUNT = 5;
    private final List<Player> players;

    public Players(List<String> playerNames, CardHand initialDeal) {
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        this.players = playerNames.stream()
                .map(name -> new Player(name, initialDeal))
                .toList();
    }

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }
}
