package blackjack.domain;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BlackJackResult {
    private final Map<String, Boolean> playerResult;
    private final Map<Boolean, Integer> dealerResult;

    private BlackJackResult(Map<String, Boolean> playerResult, Map<Boolean, Integer> dealerResult) {
        this.playerResult = unmodifiableMap(playerResult);
        this.dealerResult = unmodifiableMap(dealerResult);
    }

    public static BlackJackResult of(Player dealer, List<Player> gamblers) {
        Map<String, Boolean> playerResult = new HashMap<>();
        Map<Boolean, Integer> dealerResult = new HashMap<>();

        final List<Entry<String, Boolean>> collect = gamblers.stream()
            .map(gambler -> Map.entry(gambler.getName(), gambler.getResult() > dealer.getResult()))
            .collect(Collectors.toList());

        collect
            .forEach(entry -> playerResult.put(entry.getKey(), entry.getValue()));

        gamblers
            .forEach(gambler -> {
                if (gambler.getResult() > dealer.getResult()) {
                    dealerResult.merge(Boolean.FALSE, 1, Integer::sum);
                }
                if (gambler.getResult() < dealer.getResult()) {
                    dealerResult.merge(Boolean.TRUE, 1, Integer::sum);
                }
            });


        return new BlackJackResult(playerResult, dealerResult);
    }

    public Map<String, Boolean> getPlayerResult() {
        return playerResult;
    }

    public Map<Boolean, Integer> getDealerResult() {
        return dealerResult;
    }
}
