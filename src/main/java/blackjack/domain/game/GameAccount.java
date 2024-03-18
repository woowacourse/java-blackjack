package blackjack.domain.game;

import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameAccount {

    private static final Map<Player, Money> store = new LinkedHashMap<>();

    public void betMoney(Player player, Money money) {
        store.put(player, money);
    }

    public Money findMoney(Player player) {
        return store.get(player);
    }

    public void applyGameResults(Map<Player, GameResult> gameResults) {
        for (Player player : gameResults.keySet()) {
            Money betMoney = store.get(player);
            GameResult gameResult = gameResults.get(player);
            store.put(player, gameResult.calculateBetResult(betMoney));
        }
    }

    public Money calculateDealerIncome() {
        Money money = calculatePlayersTotalProfit();
        return money.negate();
    }

    private Money calculatePlayersTotalProfit() {
        Money playersProfit = new Money();
        for (Money playerBetMoney : store.values()) {
            playersProfit = playersProfit.add(playerBetMoney);
        }
        return playersProfit;
    }

    public Map<Player, Money> getStore() {
        return new LinkedHashMap<>(store);
    }

    public void clearStore() {
        store.clear();
    }
}
