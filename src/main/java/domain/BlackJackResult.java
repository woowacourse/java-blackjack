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
    private final Map<String, MatchResult> playerResult;
    private final Map<MatchResult, Long> dealerResult;

    private BlackJackResult(Map<String, MatchResult> playerResult, Map<MatchResult, Long> dealerResult) {
        this.playerResult = unmodifiableMap(playerResult);
        this.dealerResult = unmodifiableMap(dealerResult);
    }

    public static BlackJackResult of(Dealer dealer, Gamblers gamblers) {
        Map<String, MatchResult> playerResult = getPlayerResult(dealer, gamblers);
        Map<MatchResult, Long> dealerResult = getDealerResult(playerResult);

        return new BlackJackResult(playerResult, dealerResult);
    }

    private static Map<String, MatchResult> getPlayerResult(Dealer dealer, Gamblers gamblers) {
        return gamblers.getGamblers()
                .stream()
                .collect(toMap(Player::getName, gambler -> MatchResult.of(dealer, gambler)));
    }

    private static Map<MatchResult, Long> getDealerResult(Map<String, MatchResult> playerResult) {
        return playerResult.values()
                .stream()
                .map(MatchResult::opposite)
                .collect(groupingBy(identity(), () -> new EnumMap<>(MatchResult.class), counting()));
    }

    public Map<String, MatchResult> getPlayerResult() {
        return playerResult;
    }

    public Map<MatchResult, Long> getDealerResult() {
        return dealerResult;
    }
}
