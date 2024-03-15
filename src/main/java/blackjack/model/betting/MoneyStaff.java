package blackjack.model.betting;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.List;
import java.util.Map;

public class MoneyStaff {
    private final BettingRule bettingRule;
    private final Map<Player, Money> bettingMoneys;

    MoneyStaff(final BettingRule bettingRule, final Map<Player, Money> bettingMoneys) {
        this.bettingRule = bettingRule;
        this.bettingMoneys = bettingMoneys;
    }

    public List<Money> calculateProfitMoneys(final Map<Player, ResultCommand> playerGameResult) {
        return playerGameResult.keySet().stream()
                .map(player -> calculateProfitMoney(player, playerGameResult.get(player)))
                .toList();
    }

    public Money calculateProfitMoney(final Player player, final ResultCommand resultCommand) {
        double profitRate = bettingRule.calculateProfitRate(player, resultCommand);
        return bettingMoneys.get(player).multiple(profitRate);
    }

    public Money calculateDealerProfitAmount(final List<Money> playerProfits) {
        int playerProfit = playerProfits.stream()
                .mapToInt(Money::getValue)
                .sum();
        return new Money((-1) * playerProfit);
    }
}
