package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultTable {

    private final Map<String, GameResult> table;

    public ResultTable() {
        table = new HashMap<>();
    }

    public void put(final String name, final GameResult gameResult) {
        table.put(name, gameResult);
    }

    public GameResult get(final String name) {
        return table.get(name);
    }

    public List<String> getGameEndedPlayerNames() {
        return List.copyOf(table.keySet());
    }
}
