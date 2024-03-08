package view.dto;

import java.util.Map;

import domain.GameResultStatus;
import domain.Dealer;
import domain.DealerGameResult;

public class DealerResultDto {

    private final String name;
    private final Map<GameResultStatus, Integer> result;

    public DealerResultDto(final Dealer dealer, final DealerGameResult dealerGameResult) {
        this.name = dealer.name()
                .value();
        this.result = dealerGameResult.getResult();
    }

    public Map<GameResultStatus, Integer> getResult() {
        return Map.copyOf(result);
    }

    public String parseResult() {
        return name + ": " + result.keySet()
                .stream()
                .map(status -> result.get(status) + status.getValue())
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }
}
