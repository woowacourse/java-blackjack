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

    public void put(Player player, BettingMoney money) {
        bets.put(player, money);
    }

    public BettingResult calculateResult(BlackjackResult blackjackResult) {
        Map<Player, Money> bettingResults = bets.keySet().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> bets.get(player).multiply(blackjackResult.get(player).getRatio()),
                        (o1, o2) -> o1,
                        LinkedHashMap::new
                ));
        return new BettingResult(bettingResults);
    }

    public Map<Player, BettingMoney> getBets() {
        return Map.copyOf(bets);
    }
}
