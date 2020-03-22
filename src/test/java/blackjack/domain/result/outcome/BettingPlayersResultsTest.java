package blackjack.domain.result.outcome;

import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.result.ResultType.LOSE;
import static blackjack.domain.result.ResultType.WIN;
import static org.assertj.core.api.Assertions.assertThat;

public class BettingPlayersResultsTest {
    @DisplayName("딜러 수익 확인")
    @Test
    void test3() {
        List<BettingPlayerResult> results = new ArrayList<>();
        results.add(new BettingPlayerResult(new Name("포비"), new Money(1000), LOSE));
        results.add(new BettingPlayerResult(new Name("쪼밀리"), new Money(1000), LOSE));
        results.add(new BettingPlayerResult(new Name("타미"), new Money(1000), LOSE));
        results.add(new BettingPlayerResult(new Name("CU"), new Money(1000), WIN));

        BettingPlayersResults playersResults = new BettingPlayersResults(results);

        double actualProfit = playersResults.computeDealerResult();

        double expectedProfit = 2000;

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}
