package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.result.Result;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyTest {


    @Test
    @DisplayName("베팅 금액이 1000으로 나눠지지 않을 때 테스트")
    void isSmallerThan1000() {
        assertThatThrownBy(() -> new Money(1100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅 금액이 음수일 때 테스트")
    void nonDivisibleBy1000() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "[{index}] 베팅 금액 10000, 결과(Result): {0}, 수익: {1}")
    @MethodSource("revenueParameters")
    @DisplayName("베팅 금액에 따른 수익을 계산한다.")
    void getRevenue(Result result, int revenue) {
        Money money = new Money(10000);
        assertThat(money.getRevenue(result)).isEqualTo(revenue);
    }

    static Stream<Arguments> revenueParameters() {
        return Stream.of(
                Arguments.of(Result.BLACKJACK_WIN, 15000),
                Arguments.of(Result.WIN, 10000),
                Arguments.of(Result.DRAW, 0),
                Arguments.of(Result.LOSE, -10000)
        );
    }
}
