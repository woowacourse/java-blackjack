package player;

import card.CardDeck;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames, CardDeck cardDeck) {
        validate(playerNames);

        List<Player> players = playerNames.stream()
                .map(name -> Player.joinGame(name, cardDeck))
                .toList();

        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Name> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public int getSize() {
        return players.size();
    }

    private static void validate(List<String> players) {
        validatePlayerCountRange(players);
        validateHasDuplicateName(players);
    }

    private static void validateHasDuplicateName(List<String> players) {
        int uniqueNameCount = countPlayerUniqueName(players);

        if (players.size() != uniqueNameCount) {
            throw new IllegalArgumentException("참가자는 중복된 이름을 가질 수 없습니다.");
        }
    }

    private static int countPlayerUniqueName(List<String> players) {
        return Set.copyOf(players).size();
    }

    private static void validatePlayerCountRange(List<String> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }
}
