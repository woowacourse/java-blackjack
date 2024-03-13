package blackjack.model;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final Map<Player, Money> bets;

    public Bets() {
        this.bets = new LinkedHashMap<>();
    }

    public void addBet(final Player player, final int money) {
        validatePositiveMoney(money);
        bets.put(player, new Money(money));
    }

    private void validatePositiveMoney(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 베팅할 수 없습니다.");
        }
    }

    public Map<Player, Money> calculatePlayersProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, Money> playersProfit = new LinkedHashMap<>();
        for (Player player : result.keySet()) {
            playersProfit.put(player, calculatePlayerProfit(result, player));
        }
        return playersProfit;
    }

    private Money calculatePlayerProfit(final Map<Player, ResultCommand> result, final Player player) {
        final double multiplier = result.get(player).getRate();
        return bets.get(player).multiply(multiplier);
    }

    public Money calculateDealerProfit(final Map<Player, ResultCommand> result) {
        final Map<Player, Money> playersProfit = calculatePlayersProfit(result);
        final int playerTotalProfit = playersProfit.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
        return new Money(playerTotalProfit).multiply(-1);
    }
}
