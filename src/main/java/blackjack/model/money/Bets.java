package blackjack.model.money;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final Map<Player, BetMoney> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final int money) {
        validatePositiveMoney(money);
        bets.put(player, new BetMoney(money));
    }

    private void validatePositiveMoney(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 베팅할 수 없습니다.");
        }
    }

    public Map<Player, BetMoney> calculatePlayersProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, BetMoney> playersProfit = new LinkedHashMap<>();
        for (Player player : result.keySet()) {
            final double rate = result.get(player).getRate();
            playersProfit.put(player, calculatePlayerProfit(rate, player));
        }
        return playersProfit;
    }

    private BetMoney calculatePlayerProfit(final double rate, final Player player) {
        return bets.get(player).multiply(rate);
    }

    public BetMoney calculateDealerProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, BetMoney> playersProfit = calculatePlayersProfit(result);
        final int playerTotalProfit = playersProfit.values().stream()
                .mapToInt(BetMoney::getBetMoney)
                .sum();
        return new BetMoney(playerTotalProfit).multiply(-1);
    }
}
