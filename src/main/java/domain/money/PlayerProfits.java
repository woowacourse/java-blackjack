package domain.money;

import domain.user.Dealer;
import domain.user.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PlayerProfits {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, Profit> playersMoney;

    public PlayerProfits(Map<Player, Profit> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public void doForAllPlayers(Consumer<Player> playerAction) {
        playersMoney.keySet().forEach(playerAction);
    }

    public PlayerProfits generateMoneyResult(Dealer dealer) {
        return new PlayerProfits(playersMoney.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> moneyByGameResult(dealer, entry),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                )));
    }

    private Profit moneyByGameResult(Dealer dealer, Entry<Player, Profit> entry) {
        Profit profit = changeMoneyIfBlackjack(entry);
        return profit.change(entry.getKey().generateResult(dealer));
    }

    private Profit changeMoneyIfBlackjack(Entry<Player, Profit> entry) {
        if (entry.getKey().isBlackjack()) {
            return entry.getValue().changeByBlackjack();
        }
        return entry.getValue();
    }

    public int calculateDealerMoney() {
        return playersMoney.values()
                .stream()
                .mapToInt(Profit::value)
                .sum() * DEALER_MULTIPLIER;
    }

    public Set<Player> getPlayers() {
        return playersMoney.keySet();
    }

    public Map<Player, Profit> getPlayersMoney() {
        return Collections.unmodifiableMap(playersMoney);
    }
}
