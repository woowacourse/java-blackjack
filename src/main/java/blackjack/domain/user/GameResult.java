package blackjack.domain.user;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<OneGameResult, Integer> dealerResult = new LinkedHashMap<>();
    private final Map<Player, OneGameResult> playerResult = new LinkedHashMap<>();

    {
        for (OneGameResult potentialResult : OneGameResult.values()) {
            dealerResult.put(potentialResult, 0);
        }
    }

    public GameResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            OneGameResult playerGameResult = player.betResult(dealer);
            OneGameResult dealerOneGameResult = translateToDealer(playerGameResult);
            int resultCount = dealerResult.get(dealerOneGameResult);
            dealerResult.put(dealerOneGameResult, resultCount + 1);
            playerResult.put(player, playerGameResult);
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

    public Map<OneGameResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(this.dealerResult);
    }

    public Map<Player, OneGameResult> getPlayersResult() {
        return Collections.unmodifiableMap(this.playerResult);
    }
}
