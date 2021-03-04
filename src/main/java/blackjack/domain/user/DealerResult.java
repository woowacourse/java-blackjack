package blackjack.domain.user;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

    private final Map<OneGameResult, Integer> dealerResult = new LinkedHashMap<>();

    {
        for (OneGameResult potentialResult : OneGameResult.values()) {
            dealerResult.put(potentialResult, 0);
        }
    }

    public DealerResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            OneGameResult result = translateToDealer(player.betResult(dealer));
            int resultCount = dealerResult.get(result);
            dealerResult.put(result, resultCount + 1);
        }
    }

    private OneGameResult translateToDealer(OneGameResult result) {
        if (OneGameResult.WIN.equals(result)) {
            return OneGameResult.LOSE;
        }
        if (OneGameResult.LOSE.equals(result)) {
            return OneGameResult.WIN;
        }
        return OneGameResult.TIE;
    }

    public Map<OneGameResult, Integer> getResult() {
        return Collections.unmodifiableMap(this.dealerResult);
    }
}
