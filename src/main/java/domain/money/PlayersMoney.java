package domain.money;

import domain.Deck;
import domain.user.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PlayersMoney {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, Money> playersMoney;

    public PlayersMoney(Map<Player, Money> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public void addStartCards(Deck deck) {
        for (Player player : playersMoney.keySet()) {
            player.addStartCards(deck);
        }
    }

    public PlayersMoney changeByPlayersResult(Map<Player, GameResult> playerResults) {
        return new PlayersMoney(playersMoney.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> moneyByGameResult(playerResults, entry)
                )));
    }

    private static Money moneyByGameResult(Map<Player, GameResult> playerResults, Entry<Player, Money> entry) {
        Money money = entry.getValue();
        money = changeMoneyIfBlackjack(entry, money);
        return money.change(playerResults.get(entry.getKey()));
    }

    private static Money changeMoneyIfBlackjack(Entry<Player, Money> entry, Money money) {
        if (entry.getKey().isBlackjack()) {
            return money.changeByBlackjack();
        }
        return money;
    }

    public int calculateDealerMoney() {
        return playersMoney.values()
                .stream()
                .mapToInt(Money::value)
                .sum() * DEALER_MULTIPLIER;
    }

    public List<Player> getPlayers() {
        return playersMoney.keySet().stream().toList();
    }

    public Map<Player, Money> getPlayersMoney() {
        return Collections.unmodifiableMap(playersMoney);
    }
}
