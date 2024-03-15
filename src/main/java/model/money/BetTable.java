package model.money;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.participant.Name;

public class BetTable {
    public static final Name DEALER_NAME = new Name("딜러");
    private static final BetTable instance = new BetTable();

    private final Map<Name, Money> table;

    private BetTable() {
        table = new HashMap<>();
    }

    public static BetTable getInstance() {
        if (instance == null) {
            return new BetTable();
        }
        return instance;
    }

    public void add(Name name, Money money) {
        table.put(name, money);
    }

    public void remittanceByPolicy(Name name, DividendPolicy policy) {
        Money appliedMoney = policy.apply(table.get(name));
        remittance(name, appliedMoney);
    }

    private void remittance(Name name, Money money) {
        table.put(name, findMoneyByName(name).increase(money));
        table.put(DEALER_NAME, findMoneyByName(DEALER_NAME).decrease(money));
    }

    private Money findMoneyByName(Name name) {
        return table.getOrDefault(name, new Money(0));
    }

    public Set<Name> findAllParticipantNames() {
        return table.keySet();
    }

}
