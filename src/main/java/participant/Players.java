package participant;

import game.GameResult;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void updateMoney(Map<Player, GameResult> gameResults) {
        for (Entry<Player, GameResult> entry : gameResults.entrySet()) {
            Player player = entry.getKey();
            GameResult gameResult = entry.getValue();

            int money = gameResult.calculateEarnings(player.getBettingMoney());
            boolean isProfitable = gameResult.isProfitable();
            player.updateMoney(money, isProfitable);
        }
    }

    public Profit sumProfits() {
        return Profit.of(players.stream()
                .mapToInt(player -> player.calculateProfit().getAmount())
                .sum());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
