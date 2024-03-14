package blackjack.domain.rule;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.Money;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("베팅 결과들 도메인 테스트")
class BetResultsTest {

    @DisplayName("딜러가 얼마의 수익을 냈는지 계산할 수 있다")
    @Test
    void testCalculatePalyersProfit() {
        BetResult betResult1 = new BetResult("리비", new Money(10000));
        BetResult betResult2 = new BetResult("썬", new Money(20000));
        BetResults betResults = new BetResults(List.of(betResult1, betResult2));

        assertThat(betResults.calculateDealerProfit().getAmount()).isEqualTo(-30000);
    }
}
