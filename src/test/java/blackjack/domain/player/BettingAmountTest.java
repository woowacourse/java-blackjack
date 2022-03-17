package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.Score;

public class BettingAmountTest {

    @Nested
    @DisplayName("new는")
    class New {
            @Test
            @DisplayName("양수가 아닌 값이 들어올 때 예외를 던진다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> new BettingAmount(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("베팅금액은 0보다 커야 합니다.");
            }
    }

    @Nested
    @DisplayName("getProfit은")
    class GetProfit {
        @ParameterizedTest
        @CsvSource(value = {"WIN|1500", "DRAW|1000", "LOSE|-1000"}, delimiter = '|')
        @DisplayName("Score에 따라 수익을 반환한다.")
        void it_return_profit(Score score, double expected) {
            BettingAmount bettingAmount = new BettingAmount(1000);

            assertThat(bettingAmount.getDividend(score)).isEqualTo(expected);
        }
    }
}
