package participant;

import game.GameResult;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void updateMoney(Dealer dealer) {
        players.forEach(player -> player.updateMoney(GameResult.judgePlayerResult(dealer, player).getRate()));
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
