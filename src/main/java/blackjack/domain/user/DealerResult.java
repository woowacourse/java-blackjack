package blackjack.domain.user;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private final Map<String, Integer> dealerResult = new LinkedHashMap<>();

    {
        this.dealerResult.put("승", 0);
        this.dealerResult.put("무", 0);
        this.dealerResult.put("패", 0);
    }

    public DealerResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            String result = Result.getResult(dealer, player);
            int resultCount = dealerResult.get(result);
            dealerResult.put(result, resultCount + 1);
        }
    }

    public Map<String, Integer> getDealerResult() {
        return Collections.unmodifiableMap(this.dealerResult);
    }
}
