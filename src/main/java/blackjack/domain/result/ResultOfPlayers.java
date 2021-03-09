package blackjack.domain.result;

import blackjack.domain.WinOrLose;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ResultOfPlayers {

    private final List<ResultOfGamer> resultOfGamers;

    public ResultOfPlayers(List<ResultOfGamer> resultOfGamers) {
        this.resultOfGamers = resultOfGamers;
    }

    public ResultOfDealer getResultOfDealer() {
        return new ResultOfDealer(calculateDealerResult(), calculateDealerRevenue());
    }

    private List<WinOrLose> calculateDealerResult() {
        return resultOfGamers.stream()
                .map(ResultOfGamer::getWinOrLose)
                .map(WinOrLose::reverse)
                .collect(toList());
    }

    private long calculateDealerRevenue() {
        return -getDealerLoss();
    }

    private long getDealerLoss() {
        return resultOfGamers.stream()
                .map(ResultOfGamer::getRevenue)
                .mapToLong(Long::longValue)
                .sum();
    }

    public List<ResultOfGamer> getResultOfGamers() {
        return Collections.unmodifiableList(resultOfGamers);
    }

}
