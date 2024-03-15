package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingCashier {

    private final Map<Player, BlackjackMoney> moneyMap;
    private final BlackjackMoney dealerMoney;

    private BettingCashier(Map<Player, BlackjackMoney> moneyMap, BlackjackMoney dealerMoney) {
        this.moneyMap = moneyMap;
        this.dealerMoney = dealerMoney;
    }

    public static BettingCashier of(Betting betting, Result result) {
        Map<Player, BlackjackMoney> moneyMap = new HashMap<>();

        for (Player player : betting.getPlayers()) {
            BlackjackMoney money = betting.findMoneyOf(player);
            BlackjackMoney multipleMoney = money.applyMultiple(result.getMultiple(player));

            moneyMap.put(player, multipleMoney);
        }
        return new BettingCashier(moneyMap, calculateProfitOfDealer(moneyMap));
    }

    private static BlackjackMoney calculateProfitOfDealer(Map<Player, BlackjackMoney> moneyMap) {
        return moneyMap.values().stream()
                .map(BlackjackMoney::toNegative)
                .reduce(BlackjackMoney::add)
                .orElseThrow(IllegalStateException::new);
    }

    public BlackjackMoney findProfitOfDealer() {
        return dealerMoney;
    }

    public BlackjackMoney findProfitOf(Player player) {
        return moneyMap.get(player);
    }
}
