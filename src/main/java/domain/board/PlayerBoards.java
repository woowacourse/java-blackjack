package domain.board;

import domain.PlayerStatus;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBoards {

    public static final int MIN_PLAYER_COUNT = 1;
    public static final int MAX_PLAYER_COUNT = 7;
    private final List<PlayerBoard> playerBoards;

    private PlayerBoards(List<PlayerBoard> playerBoards) {
        this.playerBoards = playerBoards;
    }

    public static PlayerBoards of(List<String> playerNames) {
        validate(playerNames.size());
        List<PlayerBoard> playerBoards = new ArrayList<>();
        for (String playerName : playerNames) {
            playerBoards.add(new PlayerBoard(new Player(playerName), PlayerStatus.HIT_ABLE));
        }
        return new PlayerBoards(playerBoards);
    }

    private static void validate(int size) {
        if (size < MIN_PLAYER_COUNT || size > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어는 1명 이상 7명 이하입니다.");
        }
    }

    public List<Player> getPlayers() {
        return playerBoards.stream().map(PlayerBoard::getPlayer).collect(Collectors.toList());
    }

    public List<PlayerBoard> getAllPlayerBoard() {
        return List.copyOf(playerBoards);
    }

    @Override
    public String toString() {
        return "PlayerBoards{" +
            "playerBoards=" + playerBoards +
            '}';
    }
}
