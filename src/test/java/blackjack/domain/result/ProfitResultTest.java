package blackjack.domain.result;

import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.participant.Dealer;
import blackjack.domain.participant.participant.Participant;
import blackjack.domain.participant.participant.Player;
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
        final ProfitResult profitResult = new ProfitResult(
                dealer, Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE)
        );

        // When & then
        assertThat(profitResult).isEqualTo(new ProfitResult(dealer, Map.of(
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
        final ProfitResult profitResult = new ProfitResult(dealer,
                new LinkedHashMap<>(Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE, pobi, ResultStatus.PUSH,
                        norang, ResultStatus.BLACKJACK)));

        // When
        final Map<Participant, Integer> profits = profitResult.getResult();

        // Then
        Assertions.assertAll(
                () -> assertThat(profits.get(dealer)).isEqualTo(-85_000),
                () -> assertThat(profits.get(mint)).isEqualTo(-10_000),
                () -> assertThat(profits.get(mj)).isEqualTo(20_000),
                () -> assertThat(profits.get(pobi)).isEqualTo(0),
                () -> assertThat(profits.get(norang)).isEqualTo(75_000)
        );
    }
}
