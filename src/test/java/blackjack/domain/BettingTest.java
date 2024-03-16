package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("배팅 금액")
class BettingTest {

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
    @Test
    void calculateProfit() {
        // given
        Betting betting = new Betting("1000");

        // when & then
        assertAll(
                () -> assertThat(betting.calculateProfit(GameResult.BLACKJACK)).isEqualTo(new BigDecimal("1500.0")),
                () -> assertThat(betting.calculateProfit(GameResult.WIN)).isEqualTo(new BigDecimal("1000")),
                () -> assertThat(betting.calculateProfit(GameResult.DRAW)).isEqualTo(new BigDecimal("0")),
                () -> assertThat(betting.calculateProfit(GameResult.LOSE)).isEqualTo(new BigDecimal("-1000"))
        );
    }
}
