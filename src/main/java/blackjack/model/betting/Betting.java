package blackjack.model.betting;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final String NOT_FOUND_BETTING_PLAYER = "배팅 정보가 없는 플레이어입니다.";

    private final Map<String, Money> playerBettingMoney;

    public Betting() {
        this.playerBettingMoney = new HashMap<>();
    }

    public void addPlayerBettingMoney(String playerName, Money money) {
        playerBettingMoney.put(playerName, money);
    }

    public Money findBettingMoneyByPlayerName(String playerName) {
        if (!playerBettingMoney.containsKey(playerName)) {
            throw new IllegalArgumentException(NOT_FOUND_BETTING_PLAYER);
        }
        return playerBettingMoney.get(playerName);
    }
}
