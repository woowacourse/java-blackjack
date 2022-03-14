package domain;

import static java.util.Collections.unmodifiableMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import domain.player.Dealer;
import domain.player.Gamblers;
import domain.player.Player;
import java.util.EnumMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<String, MatchResult> gamblerResult;

    private BlackJackResult(Map<String, MatchResult> gamblerResult) {
        this.gamblerResult = unmodifiableMap(gamblerResult);
    }

    public static BlackJackResult of(Dealer dealer, Gamblers gamblers) {
        return new BlackJackResult(getPlayerResult(dealer, gamblers));
    }

    private static Map<String, MatchResult> getPlayerResult(Dealer dealer, Gamblers gamblers) {
        return gamblers.getGamblers()
                .stream()
                .collect(toMap(Player::getName, gambler -> MatchResult.of(dealer, gambler)));
    }

    public Map<String, MatchResult> getGamblerResult() {
        return gamblerResult;
    }

    public Map<MatchResult, Long> getDealerResult() {
        return gamblerResult.values()
                .stream()
                .map(MatchResult::opposite)
                .collect(groupingBy(identity(), () -> new EnumMap<>(MatchResult.class), counting()));
    }
}
