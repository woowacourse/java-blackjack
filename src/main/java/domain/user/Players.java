package domain.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.ErrorMessage;

public class Players {
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayers(playerNames);

        players = playerNames.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList());
    }

    private void validatePlayers(List<String> playerNames) {
        validateNameFormat(playerNames);
        validateNameDuplication(playerNames);
        validateSize(playerNames);
    }

    private void validateNameFormat(List<String> playerNames) {
        if (playerNames.stream().anyMatch(name -> name.equals("딜러"))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_NAME_FORMAT.getMessage());
        }
    }

    private void validateNameDuplication(List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_PLAYER_NAME_EXIST.getMessage());
        }
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != playerNames.stream().distinct().count();
    }

    private void validateSize(List<String> players) {
        if (players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_SIZE.getMessage());
        }
    }

    public Map<String, Boolean> getPlayerFinalResult() {
        Map<String, Boolean> playerFinalResult = new LinkedHashMap<>();

        for (Player player : players) {
            playerFinalResult.put(player.getName(), player.isWinner());
        }

        return playerFinalResult;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
