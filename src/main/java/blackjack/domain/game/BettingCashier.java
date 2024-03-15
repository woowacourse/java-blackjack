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

        betting.getBettingMap().forEach((key, value) ->
                moneyMap.put(key, value.applyMultiple(result.getMultiple(key)))
        );

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
