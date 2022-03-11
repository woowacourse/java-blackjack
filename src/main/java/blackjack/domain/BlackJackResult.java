package blackjack.domain;

import blackjack.dto.PlayerDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResult {
    private final Map<GameResult, Integer> dealerResult;
    private final LinkedHashMap<Player, GameResult> gamblerResult;

    private BlackJackResult(final Map<GameResult, Integer> dealerResult, final LinkedHashMap<Player, GameResult> gamblerResult) {
        this.dealerResult = dealerResult;
        this.gamblerResult = gamblerResult;
    }

    public static BlackJackResult of(Player dealer, List<Player> gamblers) {
        final LinkedHashMap<Player, GameResult> gamblerResult = new LinkedHashMap<>();
        final Map<GameResult, Integer> dealerResult = gamblers.stream()
            .collect(Collectors.groupingBy(
                gambler -> {
                    final GameResult currentDealderResult = dealer.compare(gambler);
                    gamblerResult.put(gambler, currentDealderResult.reverse());
                    return dealer.compare(gambler);
                },
                Collectors.summingInt(count -> 1)
            ));
        return new BlackJackResult(dealerResult, gamblerResult);
    }

    public List<String> getDealerResult() {
        return dealerResult.entrySet()
            .stream()
            .map(it -> it.getValue() + it.getKey().getResult())
            .collect(Collectors.toList());
    }

    public List<String> getGamblerResult() {
        return gamblerResult.entrySet()
            .stream()
            .map(it -> PlayerDto.from(it.getKey()).getName() + ": " + it.getValue().getResult())
            .collect(Collectors.toList());
    }
}
