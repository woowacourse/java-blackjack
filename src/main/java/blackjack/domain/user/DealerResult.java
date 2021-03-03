package blackjack.domain.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private final Map<String, Integer> dealerResult = new HashMap<>();

    public DealerResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            String result = translateToDealer(player.betResult(dealer));
            int resultCount = dealerResult.getOrDefault(result, 0);
            dealerResult.put(result, resultCount + 1);
        }
    }

    private String translateToDealer(String result) {
        if (result.equals("승")) {
            return "패";
        }
        if (result.equals("패")) {
            return "승";
        }
        return result;
    }

    public Map<String, Integer> getResult() {
        return Collections.unmodifiableMap(this.dealerResult);
    }
}
