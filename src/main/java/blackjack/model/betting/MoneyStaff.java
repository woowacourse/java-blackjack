package blackjack.model.betting;

import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MoneyStaff {
    private final BettingRule bettingRule;
    private final Map<Player, Money> bettingMoneys;

    public MoneyStaff(final BettingRule bettingRule, final Map<Player, Money> bettingMoneys) {
        this.bettingRule = bettingRule;
        this.bettingMoneys = bettingMoneys;
    }

    public Map<Player, Money> calculateProfitMoneys(final Map<Player, ResultCommand> playerGameResult) {
        Map<Player, Money> playerProfitMoneys = new LinkedHashMap<>();
        for (Player player : playerGameResult.keySet()) {
            Money profitMoney = calculateProfitMoney(player, playerGameResult.get(player));
            playerProfitMoneys.put(player, profitMoney);
        }
        return playerProfitMoneys;
    }

    private Money calculateProfitMoney(final Player player, final ResultCommand resultCommand) {
        ProfitRate profitRate = bettingRule.calculateProfitRate(player, resultCommand);
        Money money = bettingMoneys.get(player);
        return money.multiple(profitRate);
    }

    public Money calculateDealerProfitAmount(final List<Money> playerProfits) {
        int playerProfit = playerProfits.stream()
                .mapToInt(Money::getValue)
                .sum();
        return new Money((-1) * playerProfit);
    }
}
