package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import blackjack.strategy.BettingAmountStrategy;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerBettings {

    private final List<PlayerBetting> value;

    private PlayerBettings(List<PlayerBetting> value) {
        this.value = value;
    }

    public static PlayerBettings of(List<Player> players, BettingAmountStrategy strategy) {

        List<PlayerBetting> playerBettings = players.stream()
                .map(player -> initPlayerBetting(strategy, player))
                .collect(Collectors.toUnmodifiableList());

        return new PlayerBettings(playerBettings);
    }

    private static PlayerBetting initPlayerBetting(BettingAmountStrategy strategy, Player player) {
        String playerName = player.getName();
        int bettingAmount = strategy.getBettingAmount(playerName);

        return new PlayerBetting(player, bettingAmount);
    }

    public List<PlayerBetting> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PlayerBettings{" + "value=" + value + '}';
    }
}
