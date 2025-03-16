package blackjack.domain.result;

import blackjack.domain.game.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResults {
    private final Map<Player, DealerResult> dealerResults;

    public DealerResults() {
        this.dealerResults = new LinkedHashMap<>();
    }

    public void add(Player player, DealerResult dealerResult) {
        if (dealerResults.get(player) != null) {
            throw new IllegalArgumentException("이미 등록된 플레이어에 대한 결과입니다.");
        }
        this.dealerResults.put(player, dealerResult);
    }

    public DealerResult findResultByPlayer(Player player) {
        return dealerResults.entrySet().stream()
                .filter(entry -> entry.getKey().equals(player))
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("플레이어에 해당하는 결과가 존재하지 않습니다."));
    }

}
