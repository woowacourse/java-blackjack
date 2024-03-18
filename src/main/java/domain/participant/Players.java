package domain.participant;

import domain.Profit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public void forEach(Consumer<? super Player> action) {
        players.forEach(action);
    }

    public boolean isAllBust() {
        return players.stream()
                .allMatch(Player::isBust);
    }

    public Map<Player, Profit> calculateProfits(final Dealer dealer) {
        final Map<Player, Profit> result = new LinkedHashMap<>();

        for (Player player : players) {
            result.put(player, player.calculateProfitBy(dealer));
        }

        return result;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
