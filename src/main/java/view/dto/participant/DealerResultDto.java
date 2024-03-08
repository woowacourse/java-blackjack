package view.dto.participant;

import java.util.Map;

import domain.GameResultStatus;
import domain.participant.Dealer;
import domain.participant.DealerResult;

public class DealerResultDto {

    private final String name;
    private final Map<GameResultStatus, Integer> result;

    public DealerResultDto(final Dealer dealer, final DealerResult dealerResult) {
        this.name = dealer.name()
                .value();
        this.result = dealerResult.getResult();
    }

    public String parseResult() {
        return name + ": " + result.keySet()
                .stream()
                .map(status -> result.get(status) + status.getValue())
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }
}
