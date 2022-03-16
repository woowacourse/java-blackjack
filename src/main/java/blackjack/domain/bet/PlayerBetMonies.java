package blackjack.domain.bet;

import blackjack.domain.player.Player;
import java.util.Map;
import java.util.Objects;

public class PlayerBetMonies {

    private final Map<Player, BetMoney> betMonies;

    public PlayerBetMonies(Map<Player, BetMoney> betMonies) {
        this.betMonies = betMonies;
    }

    public BetMoney get(Player player) {
        return betMonies.get(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerBetMonies that = (PlayerBetMonies) o;
        return Objects.equals(betMonies, that.betMonies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(betMonies);
    }

    @Override
    public String toString() {
        return "PlayerBetMonies{" +
                "betMonies=" + betMonies +
                '}';
    }
}
