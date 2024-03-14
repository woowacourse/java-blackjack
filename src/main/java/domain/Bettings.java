package domain;

import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Bettings {

    private final Map<Player, BetAmount> playersBetting;

    public Bettings() {
        this.playersBetting = new LinkedHashMap<>();
    }

    public void save(final Player player, final BetAmount betAmount) {
        playersBetting.put(player, betAmount);
    }

    public BetAmount findBy(final Player player) {
        return playersBetting.get(player);
    }

    public BetAmount calculateBy(final Entry<Player, Result> result) {
        if (result.getValue().equals(Result.WIN_BLACKJACK)) {
            final BetAmount resultBetAmount = findBy(result.getKey()).multiply(1.5);// TODO 배팅 금액 고려
            save(result.getKey(), resultBetAmount);
            return resultBetAmount;
        }

        if (result.getValue().equals(Result.WIN)) {
            return findBy(result.getKey());
        }

        return null;
    }
}
