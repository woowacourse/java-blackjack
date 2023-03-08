package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BlackJackBettingMachine {

    private final Map<String, Integer> repository = new HashMap<>();

    public void betMoney(String name, int money) {
        repository.put(name, money);
    }

    public int findBetMoneyByName(String name) {
        Integer bettingMoney = repository.get(name);
        if (Objects.isNull(bettingMoney)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        return bettingMoney;
    }
}
