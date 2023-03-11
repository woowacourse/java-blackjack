package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultTable {

    static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "사용자의 게임 결과가 존재하지 않습니다.";

    private final Map<String, GameResult> table;

    public ResultTable() {
        table = new HashMap<>();
    }

    public void put(final String userName, final GameResult gameResult) {
        table.put(userName, gameResult);
    }

    public GameResult get(final String userName) {
        validateUserName(userName);
        return table.get(userName);
    }

    private void validateUserName(final String userName) {
        if (!table.containsKey(userName)) {
            throw new IllegalArgumentException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    public List<String> getGameEndedPlayerNames() {
        return List.copyOf(table.keySet());
    }
}
