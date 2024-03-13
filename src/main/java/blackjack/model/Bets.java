package blackjack.model;

import blackjack.model.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final Map<Player, Money> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final int money) {
        bets.put(player, new Money(money));
    }
}
