package blackjack.domain.compete;

import blackjack.domain.participant.Player;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CompeteResultGroup {
    
    private static final String RESULT_DELIMITER = " ";
    
    private final Map<Player, CompeteResult> competeResultsOfPlayer;
    
    public CompeteResultGroup(Map<Player, CompeteResult> competeResultsOfPlayer) {
        this.competeResultsOfPlayer = competeResultsOfPlayer;
    }
    
    public String getCompeteResultOfPlayer(Player player) {
        return competeResultsOfPlayer.get(player)
                                     .getCompeteResult();
    }
    
    public String getCompeteResultOfDealer() {
        final Map<CompeteResult, Long> collect = countCompeteResultOfDealer();
        return joinCompeteResultOfDealer(collect);
    }
    
    private Map<CompeteResult, Long> countCompeteResultOfDealer() {
        return competeResultsOfPlayer.values()
                                     .stream()
                                     .map(this::reverseResult)
                                     .collect(groupingBy(Function.identity(), () -> new EnumMap<>(CompeteResult.class), counting()));
    }
    
    private CompeteResult reverseResult(CompeteResult competeResult) {
        if (competeResult == CompeteResult.WIN) {
            return CompeteResult.DEFEAT;
        }
        
        if (competeResult == CompeteResult.DEFEAT) {
            return CompeteResult.WIN;
        }
        
        return CompeteResult.DRAW;
    }
    
    private String joinCompeteResultOfDealer(Map<CompeteResult, Long> collect) {
        return collect.entrySet()
                      .stream()
                      .map(entry -> entry.getValue() + entry.getKey()
                                                            .getCompeteResult())
                      .collect(Collectors.joining(RESULT_DELIMITER));
    }
}
