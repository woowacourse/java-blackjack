package blackjackgame.domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private static final String DEALER_NAME = "딜러";
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Name> names;

    public Names(List<String> playerNames) {
        validateNames(playerNames);

        names = playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void validateNames(List<String> playerNames) {
        validateDuplication(playerNames);
        validateSize(playerNames);
        validateContainDealerName(playerNames);
    }

    private void validateDuplication(List<String> playerNames) {
        if (hasDuplicatedName(playerNames)) {
            throw new IllegalArgumentException("중복되는 플레이어 이름이 존재합니다.");
        }
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != playerNames.stream().distinct().count();
    }

    private void validateSize(List<String> players) {
        if (players.size() > MAX_PLAYER_SIZE || players.size() < 1) {
            throw new IllegalArgumentException("게임을 시작하기 위해서는 최소 1명, 최대 5명의 플레이어가 필요합니다.");
        }
    }

    private static void validateContainDealerName(List<String> playerNames) {
        if (playerNames.contains(DEALER_NAME)) {
            throw new IllegalArgumentException("플레이어의 이름은 \"딜러\"가 될 수 없습니다.");
        }
    }

    public int size() {
        return names.size();
    }

    public List<Name> getNames() {
        return names;
    }
}
