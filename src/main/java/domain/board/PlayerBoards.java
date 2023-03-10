package domain.board;

import domain.user.Player;
import domain.user.PlayerStatus;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBoards {

    public static final int MIN_PLAYER_COUNT = 1;
    public static final int MAX_PLAYER_COUNT = 7;
    private final List<PlayerBoard> playerBoards;

    private PlayerBoards(List<PlayerBoard> playerBoards) {
        this.playerBoards = playerBoards;
    }

    public static PlayerBoards from(List<String> playerNames) {
        validate(playerNames);
        List<PlayerBoard> playerBoards = playerNames.stream()
            .map((playerName) -> new PlayerBoard(new Player(playerName), PlayerStatus.HIT_ABLE))
            .collect(Collectors.toList());
        return new PlayerBoards(playerBoards);
    }

    private static void validate(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("플레이어는 1명 이상 7명 이하입니다.");
        }
        final int playerCount = playerNames.size();
        if (playerCount < MIN_PLAYER_COUNT || playerCount > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어는 1명 이상 7명 이하입니다.");
        }
        if (playerNames.stream().distinct().count() != playerCount) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return playerBoards.stream().map(PlayerBoard::getPlayer).collect(Collectors.toList());
    }

    public List<PlayerBoard> getAllPlayerBoard() {
        return List.copyOf(playerBoards);
    }
}
