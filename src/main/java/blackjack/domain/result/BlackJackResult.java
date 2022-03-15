package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BlackJackResult {

    private static final int DEALER_CACULATE_UNIT = -1;

    public final Map<Gamer, ResultStrategy> result;

    public BlackJackResult(final Map<Gamer, ResultStrategy> result) {
        this.result = result;
    }

    public Map<Gamer, Integer> calculateReturn() {
        return result.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        result -> result.getValue().calculateBet(result.getKey().getBet().getAmount()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public int calculateDealerReturn(final Map<Gamer, Integer> returnBoard) {
        return returnBoard.values()
                .stream()
                .mapToInt(value -> value)
                .sum() * DEALER_CACULATE_UNIT;
    }

    public Map<Gamer, ResultStrategy> getResult() {
        return result;
    }
}
