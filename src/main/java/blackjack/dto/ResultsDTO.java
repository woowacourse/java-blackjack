package blackjack.dto;

import blackjack.model.Results;
import java.util.List;

public final class ResultsDTO {
    private final List<ResultDTO> values;
    private final int dealerWinCount;
    private final int dealerLoseCount;

    private ResultsDTO(List<ResultDTO> values, int dealerWinCount) {
        this.values = List.copyOf(values);
        this.dealerWinCount = dealerWinCount;
        this.dealerLoseCount = values.size() - dealerWinCount;
    }

    public static ResultsDTO from(Results results) {
        return new ResultsDTO(ResultDTO.from(results.getValues()), results.countDealerWin());
    }

    public List<ResultDTO> getValues() {
        return values;
    }

    public int getDealerWinCount() {
        return dealerWinCount;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }
}
