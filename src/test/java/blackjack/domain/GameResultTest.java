package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    @DisplayName("플레이어 수익 추가 후 딜러 수익은 반대 부호로 반환")
    void getDealerProfit() {
        // given
        gameResult.add("흑곰", new BigDecimal("1000"));

        // when
        BigDecimal dealerProfit = gameResult.getDealerProfit();

        // then
        assertThat(dealerProfit).isEqualByComparingTo(new BigDecimal("-1000"));
    }

    @Test
    @DisplayName("여러 플레이어 수익 합산의 반대 부호가 딜러 수익")
    void getDealerProfitWithMultiplePlayers() {
        // given
        gameResult.add("흑곰", new BigDecimal("1000"));
        gameResult.add("밀란", new BigDecimal("-500"));

        // when
        BigDecimal dealerProfit = gameResult.getDealerProfit();

        // then
        assertThat(dealerProfit).isEqualByComparingTo(new BigDecimal("-500"));
    }

    @Test
    @DisplayName("플레이어 수익이 모두 0이면 딜러 수익도 0")
    void getDealerProfitWhenDraw() {
        // given
        gameResult.add("흑곰", BigDecimal.ZERO);
        gameResult.add("밀란", BigDecimal.ZERO);

        // when
        BigDecimal dealerProfit = gameResult.getDealerProfit();

        // then
        assertThat(dealerProfit).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
