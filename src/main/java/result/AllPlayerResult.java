package result;

import java.util.Collections;
import java.util.List;
import participant.value.Money;

public record AllPlayerResult(List<PlayerResult> playerResults) {

    public static AllPlayerResult from(List<PlayerResult> allPlayerResultInfo) {
        return new AllPlayerResult(allPlayerResultInfo);
    }

    @Override
    public List<PlayerResult> playerResults() {
        return Collections.unmodifiableList(playerResults);
    }

    public Money calculateDealerProfit() {
        return playerResults.stream()
                .map(PlayerResult::calculateProfit)
                .reduce(Money.ZERO, Money::add)
                .negate();
    }
}
