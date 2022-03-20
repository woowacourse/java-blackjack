package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.Profit;

import java.util.Map;
import java.util.stream.Collectors;

public class BettingResultDto {

    private final Map<Player, Profit> playerResult;
    private final Profit dealerResult;

    private BettingResultDto(Map<Player, Profit> playerResult, Profit dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public static BettingResultDto toDto(BettingResult result) {
        return new BettingResultDto(result.getPlayerResult(), result.getDealerResult());
    }

    public Map<String, Double> getPlayerResult() {
        return playerResult.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(),
                        e -> e.getValue().getAmount()));
    }

    public double getDealerResult() {
        return dealerResult.getAmount();
    }
}
