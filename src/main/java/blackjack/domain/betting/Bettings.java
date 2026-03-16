package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.Map;

public class Bettings {

    private final Map<Player, BettingAmount> bettings;

    public Bettings(Map<Player, BettingAmount> bettings) {
        this.bettings = bettings;
    }

    public static Bettings of(Map<Player, BettingAmount> bettings) {
        return new Bettings(bettings);
    }

    public BettingAmount findByPlayer(Player player) {
        return bettings.get(player);
    }

}
