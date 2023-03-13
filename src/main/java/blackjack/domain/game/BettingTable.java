package blackjack.domain.game;

import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BettingTable {
    private final Map<Player, BettingMoney> bets;

    public BettingTable() {
        this.bets = new LinkedHashMap<>();
    }

    public BettingTable(Map<Player, BettingMoney> bets) {
        this.bets = new LinkedHashMap<>(bets);
    }

    public BettingTable put(Player player, BettingMoney money) {
        bets.put(player, money);
        return new BettingTable(bets);
    }

    public BettingResult calculateResult(BlackjackResult blackjackResult) {
        Map<Player, Money> bettingResults = bets.keySet().stream()
                .collect(Collectors.toMap(
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
