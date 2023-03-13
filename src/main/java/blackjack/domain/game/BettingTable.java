package blackjack.domain.game;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {
    private final Map<Player, BettingMoney> bets;

    public BettingTable() {
        this.bets = Map.of();
    }

    public BettingTable(Map<Player, BettingMoney> bets) {
        this.bets = bets;
    }

    public BettingTable put(Player player, BettingMoney money) {
        Map<Player, BettingMoney> newBets = new LinkedHashMap<>();
        for (Player playerBefore : bets.keySet()) {
            newBets.put(playerBefore, bets.get(playerBefore));
        }
        newBets.put(player, money);
        return new BettingTable(Collections.unmodifiableMap(newBets));
    }

    public BettingResult calculateResult(BlackjackResult blackjackResult) {
        Map<Player, Money> bettingResults = bets.keySet().stream()
                .collect(toMap(
                        player -> player,
                        player -> getBettingMoney(player).calculateProfit(blackjackResult.getRatio(player)),
                        (o1, o2) -> o1,
                        LinkedHashMap::new
                ));
        return new BettingResult(bettingResults);
    }

    private BettingMoney getBettingMoney(Player player) {
        return bets.get(player);
    }

    public Map<Player, BettingMoney> getBets() {
        return Map.copyOf(bets);
    }
}
