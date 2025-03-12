package blackjack.model.participant;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class GamePlayers implements Iterable<Player> {

    private final List<Player> players;

    private GamePlayers(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public static GamePlayers createForNewGame(List<Player> players) {
        validatePlayers(players);
        return new GamePlayers(players);
    }

    private static void validatePlayers(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("최소 1명 이상의 플레이어가 있어야 합니다.");
        }
        boolean hasDuplicatePlayer = players.size() != new HashSet<>(players).size();
        if (hasDuplicatePlayer) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
        validateExistHandPlayers(players);
    }

    private static void validateExistHandPlayers(List<Player> players) {
        for (Player player : players) {
            validatePlayer(player);
        }
    }

    private static void validatePlayer(Player player) {
        boolean hasHand = !player.getHand().isEmpty();
        if (hasHand) {
            throw new IllegalArgumentException("이미 패를 가진 플레이어가 존재합니다.");
        }
    }

    public int participantCount() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
