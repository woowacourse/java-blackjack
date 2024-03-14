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
            Money money = store.get(player);
            GameResult gameResult = gameResults.get(player);
            Money gameResultMoney = money.multipleRatio(gameResult.getRatio());
            store.put(player, gameResultMoney);
        }
    }

    public Money calculateDealerIncome() {
        int dealerIncome = 0;
        for (Money money : store.values()) {
            dealerIncome += money.value();
        }
        return new Money(-dealerIncome);
    }
}
