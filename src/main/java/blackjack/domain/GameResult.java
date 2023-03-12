package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GameResult {
    private final Map<Player, ResultType> bettingResult;

    public GameResult(final Dealer dealer, final List<Player> players) {
        this.bettingResult = new LinkedHashMap<>();
        players.forEach(player -> bettingResult.put(player, dealer.judgePlayerResult(player)));
    }

    public int getDealerRevenue() {
        return bettingResult.entrySet().stream()
                .map(getReverseBettingMoney())
                .reduce(0, Integer::sum);
    }

    private Function<Map.Entry<Player, ResultType>, Integer> getReverseBettingMoney() {
        return entry -> ResultType.getReverseType(entry.getValue()).getRevenue(entry.getKey().getBettingMoney());
    }

    public Map<Player, ResultType> getBettingResult() {
        return new LinkedHashMap<>(bettingResult);
    }
}
