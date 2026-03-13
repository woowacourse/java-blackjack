package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Players {
    private static final int MAX_PLAYER = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = new ArrayList<>(players);
    }

    public void forEach(Consumer<Player> action) {
        //TODO: Player 객체 리스트의 forEach를 위임.
        //TODO: Consumer-값을 받아서 소비만하고 아무것도 반환하지 않는 함수. action은 각각의 player에 대해 수행할 행위 그 자체.
        players.forEach(action);
    }

    public static void validatePlayersNumber(List<Player> players) {
        validateMinimumPlayers(players);
        validateMaximumPlayers(players);
        validateDuplicateName(players);
    }

    private static void validateMaximumPlayers(List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }

    private static void validateDuplicateName(List<Player> players) {
        long uniqueCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (players.size() != uniqueCount) {
            throw new IllegalArgumentException("중복된 참가자 이름이 있습니다!");
        }
    }
}
