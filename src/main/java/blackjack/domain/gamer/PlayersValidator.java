package blackjack.domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayersValidator {
    private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";
    private static final int MIN_PLAYER_SIZE = 2;
    private static final int MAX_PLAYER_SIZE = 8;

    public static void validate(List<Player> players) {
        validateDuplicationNames(players);
        validatePlayerSize(players);
    }

    private static void validatePlayerSize(List<Player> players) {
        if (isSuitableSize(players)) {
            throw new IllegalArgumentException("플레이어의 인원은 최소 2인 ~ 최대 8인 입니다.");
        }
    }

    private static boolean isSuitableSize(List<Player> players) {
        return players.size() < MIN_PLAYER_SIZE || players.size() > MAX_PLAYER_SIZE;
    }

    private static void validateDuplicationNames(List<Player> players) {
        Set<Player> duplicationCheck = new HashSet<>(players);
        if (duplicationCheck.size() != players.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
        }
    }
}
