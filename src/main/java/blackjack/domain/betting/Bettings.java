package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.domain.state.State;
import java.util.Map;

public class Bettings {

    private final Map<Player, BettingAmount> bettings;

    public Bettings(Map<Player, BettingAmount> bettings) {
        this.bettings = bettings;
    }

    public static Bettings of(Map<Player, BettingAmount> bettings) {
        return new Bettings(bettings);
    }

    public double calculateProfit(Player player, State state) {
        BettingAmount bettingAmount = bettings.get(player);
        return state.apply(bettingAmount.amount());
    }

}
