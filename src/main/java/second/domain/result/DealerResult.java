package second.domain.result;

import first.domain.gamer.Dealer;

import java.util.List;

public class DealerResult {
    private final String name;
    private final List<ResultType> resultTypes;

    public DealerResult(List<ResultType> resultTypes) {
        this.name = Dealer.DEALER_NAME;
        this.resultTypes = resultTypes;
    }
}
