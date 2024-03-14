package blackjack.money;

import blackjack.resultstate.MatchResult;
import java.util.HashMap;
import java.util.Map;

public class BetTable {

    private final Map<String, Money> betMoney;

    public BetTable() {
        this.betMoney = new HashMap<>();
    }

    public void placeBet(String playerName, Money money) {
        betMoney.put(playerName, money);
    }

    public Money calculateProfitByName(String name, MatchResult result) {
        Money playerBetMoney = getBetMoneyByName(name);
        return result.calculateProfit(playerBetMoney);
    }

    private Money getBetMoneyByName(String name) {
        if (!betMoney.containsKey(name)) {
            throw new IllegalArgumentException("[ERROR] 배팅 정보가 존재하지 않습니다.");
        }
        return betMoney.get(name);
    }
}
