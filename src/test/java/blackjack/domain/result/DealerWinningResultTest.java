package blackjack.domain.result;

import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.providePlayer;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.gamer.Dealer;
import blackjack.domain.participant.gamer.Gamer;
import blackjack.domain.participant.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerWinningResultTest {

    @Test
    void 딜러_우승_결과를_조회한다() {
        // Given
        Player mint = providePlayer("밍트", 10_000);
        Player mj = providePlayer("엠제이", 20_000);
        DealerWinningResult winningResult = new DealerWinningResult(
                Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE)
        );

        // When & then
        assertThat(winningResult).isEqualTo(new DealerWinningResult(Map.of(
                mint, ResultStatus.WIN,
                mj, ResultStatus.LOSE
        )));
    }


    @Test
    @DisplayName("수익을 계산한다")
    void calculateProfit() {
        // Given
        Dealer dealer = new Dealer(provideEmptyCards());
        Player mint = providePlayer("밍트", 10_000);
        Player mj = providePlayer("엠제이", 20_000);
        Player pobi = providePlayer("포비", 30_000);
        Player norang = providePlayer("노랑", 50_000);
        DealerWinningResult winningResult = new DealerWinningResult(
                new LinkedHashMap<>(Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE, pobi, ResultStatus.PUSH,
                        norang, ResultStatus.BLACKJACK)));

        // When
        Map<Gamer, Integer> profits = winningResult.calculateProfit(dealer);

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
