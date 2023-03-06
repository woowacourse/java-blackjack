package blackjackgame.domain.user;

import blackjackgame.domain.game.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import blackjackgame.view.ErrorMessage;

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
        validateNameDuplication(playerNames);
        validateSize(playerNames);
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
        if (players.size() > MAX_PLAYER_SIZE || players.size() < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_SIZE.getMessage());
        }
    }

    public Map<String, Result> getPlayerFinalResult() {
        Map<String, Result> playerFinalResult = new LinkedHashMap<>();

        for (Player player : players) {
            playerFinalResult.put(player.getName(), player.getResult());
        }

        return playerFinalResult;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
