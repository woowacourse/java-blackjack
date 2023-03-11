package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<String, Money> bettingTable = new LinkedHashMap<>();

    public void add(String name, Money money) {
        bettingTable.put(name, money);
    }

    public void calculate(Map<String, ResultType> playerResult) {
        for (String name : playerResult.keySet()) {
            ResultType resultType = playerResult.get(name);
            Money money = bettingTable.get(name);
            bettingTable.put(name, resultType.calculateBetting(money));
        }
    }

    public int sum() {
        return bettingTable.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
    }

    public Map<String, Money> getBettingTable() {
        return bettingTable;
    }

}
