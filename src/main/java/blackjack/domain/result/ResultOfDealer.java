package blackjack.domain.result;

import blackjack.domain.WinOrLose;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ResultOfDealer {
    private final List<WinOrLose> winOrLoses;
    private final long revenue;

    public ResultOfDealer(List<WinOrLose> winOrLoses, long revenue) {
        this.winOrLoses = winOrLoses;
        this.revenue = revenue;
    }

    public List<String> getWinOrLosesAsString() {
        return winOrLoses.stream()
                .map(WinOrLose::getMessage)
                .collect(toList());
    }

    public long getRevenue() {
        return revenue;
    }
}
