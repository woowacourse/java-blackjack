package domain.bet;

import domain.Result;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public BetAmount calculatePlayerProfitBy(final Player player, final Dealer dealer) {
        final BetAmount betAmount = Result.calculate(player.getHands(), dealer.getHands(), findBy(player));
        save(player, betAmount);
        return betAmount;
    }
}
