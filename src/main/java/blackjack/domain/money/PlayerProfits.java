package blackjack.domain.money;

import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerProfits {
    private final Map<Player, Profit> profits;

    public PlayerProfits(Map<Player, Profit> profits) {
        this.profits = new HashMap<>(profits);
    }

    public Long getDealerProfits() {
        Long totalProfitsAmount = profits.values().stream()
                .map(Profit::getAmount)
                .reduce(0L, Long::sum);

        return -totalProfitsAmount;
    }

    @Override
    public String toString() {
        return "PlayerProfits{" +
                "profits=" + profits +
                '}';
    }

    public Map<Player, Profit> getProfits() {
        return Collections.unmodifiableMap(profits);
    }
}
