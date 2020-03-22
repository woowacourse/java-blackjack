package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayersFactory {
    public static Players create(List<String> names, List<Integer> betAmounts) {
        List<Player> players = new ArrayList<>();
        validateNames(names);
        validateBetAmounts(betAmounts);
        validateNamesSizeEqualToBetAmounts(names, betAmounts);

        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), betAmounts.get(i)));
        }
        return new Players(players);
    }

    private static void validateNames(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
    }

    private static void validateBetAmounts(List<Integer> betAmounts) {
        if (betAmounts == null || betAmounts.isEmpty()) {
            throw new IllegalArgumentException("입력된 배팅금액이 없습니다.");
        }
    }

    private static void validateNamesSizeEqualToBetAmounts(List<String> names,
        List<Integer> betAmounts) {
        if (names.size() != betAmounts.size()) {
            throw new IllegalArgumentException("이름의 수와 배팅금액 수가 같아야 합니다.");
        }
    }
}
