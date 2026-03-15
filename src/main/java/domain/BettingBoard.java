package domain;

import domain.participant.Player;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    private final Map<Player, Bet> betsByPlayer = new HashMap<>();

    public void addBetting(Player player, Bet bet) {
        betsByPlayer.put(player, bet);
    }

    public Profit calculateProfit(Player player, BigDecimal profitRate) {
        Bet originalBet = betsByPlayer.get(player);
        return new Profit(originalBet.amount().multiply(profitRate));
    }

    public Bet getBet(Player player) {
        return betsByPlayer.get(player);
    }
}
