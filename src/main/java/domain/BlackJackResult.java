package domain;

import static java.util.Collections.unmodifiableMap;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.ResultDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<String, ResultDto> blackjackResult;

    private BlackJackResult(Map<String, ResultDto> blackjackResult) {
        this.blackjackResult = unmodifiableMap(blackjackResult);
    }

    public static BlackJackResult of(Dealer dealer, Gamblers gamblers) {
        return new BlackJackResult(getBlackjackResult(dealer, gamblers));
    }

    private static Map<String, ResultDto> getBlackjackResult(Dealer dealer, Gamblers gamblers) {
        Map<String, ResultDto> blackjackResult = new LinkedHashMap<>();
        blackjackResult.put(dealer.getName(), ResultDto.of(dealer, gamblers.getGamblers()));

        for (Gambler gambler : gamblers.getGamblers()) {
            blackjackResult.put(gambler.getName(), ResultDto.of(gambler, dealer));
        }

        return blackjackResult;
    }

    public Map<String, ResultDto> getBlackjackResult() {
        return blackjackResult;
    }
}
