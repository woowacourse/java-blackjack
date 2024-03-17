package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("배팅 금액")
class BettingTest {

    private static Stream<Arguments> calculateProfitSource() {
        return Stream.of(
                Arguments.arguments(GameResult.BLACKJACK, new BigDecimal("1500.0")),
                Arguments.arguments(GameResult.WIN, new BigDecimal("1000")),
                Arguments.arguments(GameResult.DRAW, new BigDecimal("0")),
                Arguments.arguments(GameResult.LOSE, new BigDecimal("-1000"))
        );
    }

    @DisplayName("배팅 금액이 0 초과의 자연수가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"0", "-1", "abc"})
    void createBettingException(String betting) {
        // when & then
        assertThatThrownBy(() -> new Betting(betting))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임 결과에 따른 수익률을 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateProfitSource")
    void calculateProfit(final GameResult gameResult, final BigDecimal expected) {
        // given
        Betting betting = new Betting("1000");

        // when
        BigDecimal actual = betting.calculateProfit(gameResult);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
