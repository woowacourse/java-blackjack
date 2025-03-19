package blackjack.domain.result;

import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitResultTest {

    @Test
    void 딜러_우승_결과를_조회한다() {
        // Given
        final Dealer dealer = new Dealer();
        final Player mint = providePlayer("밍트", 10_000);
        final Player mj = providePlayer("엠제이", 20_000);
        final ProfitResult profitResult = ProfitResult.from(
                dealer, Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE)
        );

        // When & then
        assertThat(profitResult).isEqualTo(ProfitResult.from(dealer, Map.of(
                mint, ResultStatus.WIN,
                mj, ResultStatus.LOSE
        )));
    }

    @Test
    @DisplayName("수익을 계산한다")
    void calculateProfit() {
        // Given
        final Dealer dealer = new Dealer(provideEmptyCards());
        final Player mint = providePlayer("밍트", 10_000);
        final Player mj = providePlayer("엠제이", 20_000);
        final Player pobi = providePlayer("포비", 30_000);
        final Player norang = providePlayer("노랑", 50_000);
        final ProfitResult profitResult = ProfitResult.from(dealer,
                new LinkedHashMap<>(Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE, pobi, ResultStatus.PUSH,
                        norang, ResultStatus.BLACKJACK)));

        // When
        final Map<Participant, BigDecimal> profits = profitResult.getResult();

        // Then
        Assertions.assertAll(
                () -> assertThat(profits.get(dealer)).isEqualTo(new BigDecimal("-65000.0")),
                () -> assertThat(profits.get(mint)).isEqualTo(new BigDecimal("10000.0")),
                () -> assertThat(profits.get(mj)).isEqualTo(new BigDecimal("-20000.0")),
                () -> assertThat(profits.get(pobi)).isEqualTo(new BigDecimal("0.0")),
                () -> assertThat(profits.get(norang)).isEqualTo(new BigDecimal("75000.0"))
        );
    }
}
