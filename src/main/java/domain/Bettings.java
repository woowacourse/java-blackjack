package domain;

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

    public BetAmount calculate(final Player player, final Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            final BetAmount betAmount = findBy(player).multiply(1.5);// TODO 배팅 금액 고려
            save(player, betAmount);
            return betAmount;
        }

        return findBy(player);
    }
}
