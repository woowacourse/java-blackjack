package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.strategy.BettingAmountSupplier;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBettings {

    private final List<PlayerBetting> value;

    private PlayerBettings(List<PlayerBetting> value) {
        this.value = value;
    }

    public static PlayerBettings of(List<Player> players, BettingAmountSupplier supplier) {

        List<PlayerBetting> playerBettings = players.stream()
                .map(player -> initPlayerBetting(supplier, player))
                .collect(Collectors.toUnmodifiableList());

        return new PlayerBettings(playerBettings);
    }

    private static PlayerBetting initPlayerBetting(BettingAmountSupplier supplier,
                                                   Player player) {
        return new PlayerBetting(player, supplier.getBettingAmount());
    }

    public List<PlayerBetting> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PlayerBettings{" + "value=" + value + '}';
    }
}
