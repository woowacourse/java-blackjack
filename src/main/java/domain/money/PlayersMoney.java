package domain.money;

import domain.Deck;
import domain.player.Hand;
import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import view.Command;

public class PlayersMoney {
    public static final int DEALER_MULTIPLIER = -1;
    private final Map<Player, Money> playersMoney;

    public PlayersMoney(Map<Player, Money> playersMoney) {
        this.playersMoney = playersMoney;
    }

    public void doPlayerTurn(Function<String, Command> commandFunction, Deck deck) {
        for (Player player : playersMoney.keySet()) {
            player.receiveCard(commandFunction, deck);
        }
    }

    public PlayersMoney changeByGameResult(Hand dealerHand) {
        return new PlayersMoney(playersMoney.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> moneyByGameResult(dealerHand, entry),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                )));
    }

    private Money moneyByGameResult(Hand dealerHand, Entry<Player, Money> entry) {
        Money money = changeMoneyIfBlackjack(entry);
        return money.change(entry.getKey().generatePlayerResult(dealerHand));
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

    public Set<Player> getPlayers() {
        return playersMoney.keySet();
    }

    public void forEach(BiConsumer<Player, Money> biConsumer) {
        playersMoney.forEach(biConsumer);
    }
}
