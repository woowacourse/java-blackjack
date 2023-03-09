package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BlackJackBettingDeposit {

    private final Map<String, BettingMoney> deposit = new HashMap<>();

    public void betMoney(String name, BettingMoney bettingMoney) {
        deposit.put(name, bettingMoney);
    }

    public BettingMoney findBetMoneyByName(String name) {
        BettingMoney bettingMoney = deposit.get(name);
        if (Objects.isNull(bettingMoney)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        return bettingMoney;
    }
}
