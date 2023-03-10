package domain.game;

import domain.participant.Player;
import java.util.HashMap;

public class Bets {
    private final HashMap<Player, Money> bets;

    public Bets() {
        this.bets = new HashMap<>();
    }

    public void addBet(final Player player, final Money money) {
        bets.put(player, money);
    }

    public HashMap<Player, Money> getBets() {
        return new HashMap<>(bets);
    }
}
