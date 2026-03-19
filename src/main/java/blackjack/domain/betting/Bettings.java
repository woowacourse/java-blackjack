package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.domain.state.State;
import java.util.Map;
import java.util.Optional;

public class Bettings {

    private final Map<Player, BettingAmount> bettings;

    public Bettings(Map<Player, BettingAmount> bettings) {
        this.bettings = bettings;
    }

    public static Bettings of(Map<Player, BettingAmount> bettings) {
        return new Bettings(bettings);
    }

    public double calculateProfit(Player player, State state) {
        BettingAmount bettingAmount = findByPlayer(player)
                .orElseThrow(() -> new IllegalArgumentException("없는 플레이어입니다."));
        return state.apply(bettingAmount.amount());
    }

    private Optional<BettingAmount> findByPlayer(Player player) {
        return Optional.ofNullable(bettings.get(player));
    }

}
