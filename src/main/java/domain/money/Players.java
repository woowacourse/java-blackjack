package domain.money;

import domain.user.Dealer;
import domain.user.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Players {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, Money> playersMoney;

    public Players(Map<Player, Money> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public Players generateMoneyResult(Dealer dealer) {
        return new Players(playersMoney.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> moneyByGameResult(dealer, entry),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                )));
    }

    private Money moneyByGameResult(Dealer dealer, Entry<Player, Money> entry) {
        Money money = changeMoneyIfBlackjack(entry);
        return money.change(entry.getKey().generateResult(dealer));
    }

    private Money changeMoneyIfBlackjack(Entry<Player, Money> entry) {
        if (entry.getKey().isBlackjack()) {
            return entry.getValue().changeByBlackjack();
        }
        return entry.getValue();
    }

    public int calculateDealerMoney() {
        return playersMoney.values()
                .stream()
                .mapToInt(Money::value)
                .sum() * DEALER_MULTIPLIER;
    }

    public Set<Player> keySet() {
        return playersMoney.keySet();
    }

    public void forEach(BiConsumer<Player, Money> biConsumer) {
        playersMoney.forEach(biConsumer);
    }
}
