package domain.participant;

import java.util.*;

import static util.BlackJackConstant.MAX_PLAYER_SIZE;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validateSize(playerNames);
        validateDuplicateNames(playerNames);
        this.players = from(playerNames);
    }

    public List<Player> getPlayers() {
        return players;
    }

    private List<Player> from(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return players;
    }

    private void validateSize(List<String> players) {
        if (players.size() > MAX_PLAYER_SIZE)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }

    private void validateDuplicateNames(List<String> players) {
        Set<String> uniqNames = new HashSet<>(players);

        if (uniqNames.size() != players.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
}
