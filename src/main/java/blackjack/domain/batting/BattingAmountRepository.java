package blackjack.domain.batting;

import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BattingAmountRepository {
    private final Map<Player, BattingAmount> store;

    public BattingAmountRepository() {
        this.store = new LinkedHashMap<>();
    }

    public void save(final Player player, final BattingAmount battingAmount) {
        store.put(player, battingAmount);
    }

    public BattingAmount findByPlayer(final Player player) {
        return store.get(player);
    }
}
