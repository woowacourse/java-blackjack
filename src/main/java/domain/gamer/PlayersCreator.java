package domain.gamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayersCreator {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    public Players create(List<String> playerNames, List<Integer> moneys) {
        validate(playerNames);
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            Name name = new Name(playerNames.get(i));
            Money money = new Money(moneys.get(i));
            playerList.add(new Player(name, money));
        }
        return new Players(playerList);
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
