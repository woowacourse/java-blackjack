package domain.participant;

import domain.amount.Amount;
import domain.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Players {

    private final List<Player> names;

    public Players(final List<Player> names) {
        this.names = names;
    }

    public void forEach(Consumer<? super Player> action) {
        names.forEach(action);
    }

    public boolean isAllBust() {
        return names.stream()
                .allMatch(Player::isBust);
    }

    public Map<Player, GameResult> getPlayersResult(final Dealer dealer) {
        final Map<Player, GameResult> result = new LinkedHashMap<>();
        for (Player name : names) {
            result.put(name, name.calculateResult(dealer));
        }
        return result;
    }

    public Map<Player, Amount> calculateResult(final Dealer dealer) {
        final Map<Player, Amount> playerAmountMap = new LinkedHashMap<>();

        for (Player player : names) {
            GameResult gameResult = player.calculateResult(dealer);
            playerAmountMap.put(player, gameResult.apply(player.getBetAmount()));
        }
        return playerAmountMap;
    }

    public List<Player> getPlayers() {
        return names;
    }
}
