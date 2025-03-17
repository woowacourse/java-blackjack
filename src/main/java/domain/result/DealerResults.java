package domain.result;

import domain.BlackJackWinningStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DealerResults {
    private final List<DealerWinningStatus> dealerResult;

    public DealerResults(List<DealerWinningStatus> dealerResult) {
        this.dealerResult = new ArrayList<>(dealerResult);
    }

    public int getStatusCount(BlackJackWinningStatus blackJackWinningStatus) {
        return (int) dealerResult.stream()
                .filter(dealerWinningStatus -> dealerWinningStatus.status() == blackJackWinningStatus)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DealerResults that = (DealerResults) o;
        return Objects.equals(dealerResult, that.dealerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dealerResult);
    }

    @Override
    public String toString() {
        return "DealerResult{" +
                "dealerResult=" + dealerResult +
                '}';
    }
}
