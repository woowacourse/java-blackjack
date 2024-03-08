package player;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Name> playerNames) {
        validate(playerNames);

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    private static void validate(List<Name> players) {
        validatePlayerCountRange(players);
        validateHasDuplicateName(players);
    }

    private static void validateHasDuplicateName(List<Name> players) {
        int uniqueNameCount = countPlayerUniqueName(players);

        if (players.size() != uniqueNameCount) {
            throw new IllegalArgumentException("참가자는 중복된 이름을 가질 수 없습니다.");
        }
    }

    private static int countPlayerUniqueName(List<Name> players) {
        return players.stream()
                .map(Name::getValue)
                .collect(Collectors.toSet())
                .size();
    }

    private static void validatePlayerCountRange(List<Name> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }

    public Player getSinglePlayer(int index) {
        return players.get(index);
    }

    public int getSize() {
        return players.size();
    }
}
