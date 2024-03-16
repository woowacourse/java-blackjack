package domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayersCreator {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    public Players create(List<String> playerNames, List<Integer> moneys) {
        validate(playerNames);
        return new Players(playerNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private void validate(List<String> players) {
        validatePlayerCountRange(players);
        validateHasDuplicateName(players);
    }

    private void validateHasDuplicateName(List<String> players) {
        int uniqueNameCount = countPlayerUniqueName(players);

        if (players.size() != uniqueNameCount) {
            throw new IllegalArgumentException("참가자는 중복된 이름을 가질 수 없습니다.");
        }
    }

    private int countPlayerUniqueName(List<String> players) {
        Set<String> names = new HashSet<>(players);
        return names.size();
    }

    private void validatePlayerCountRange(List<String> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }
}
