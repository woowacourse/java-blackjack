package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private static final String DELIMITER = ",";
    private static final String MAX_PLAYER_EXCEPTION = "플레이어는 5명까지만 입력해주세요.";
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players = new ArrayList<>();

    public Players(String playersInput) {
        enterPlayer(playersInput);
    }

    private void enterPlayer(String playersInput) {
        List<String> playerNames = Arrays.asList(playersInput.split(DELIMITER));
        validateMaxPlayer(playerNames);
        for (String playerName : playerNames) {
            players.add(new Player(playerName.trim()));
        }
    }

    private void validateMaxPlayer(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(MAX_PLAYER_EXCEPTION);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
