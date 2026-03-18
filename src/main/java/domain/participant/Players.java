package domain.participant;

import java.util.*;

import static util.BlackJackConstant.MAX_PLAYER_SIZE;

public class Players {

    private final List<Player> players;

    public Players(Map<String, Integer> players) {
        validateSize(players);
        this.players = from(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    private List<Player> from(Map<String, Integer> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String name : playerNames.keySet()) {
            players.add(new Player(name, playerNames.get(name)));
        }

        return players;
    }

    private void validateSize(Map<String, Integer> players) {
        if (players.size() > MAX_PLAYER_SIZE)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }
}
