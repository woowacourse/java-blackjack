package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @Test
    @DisplayName("베팅금액 생성 테스트")
    void createBettingAmount() {
        assertThat(new BettingAmount(3000)).isNotNull();
    }

    @ParameterizedTest(name = "베팅 금액이 정수가 아닌 경우 예외 발생")
    @ValueSource(strings = {"", " ", "a", "1.5"})
    void checkNotInteger(String value) {
        assertThatThrownBy(() -> new BettingAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 정수여야 합니다.");
    }

    @ParameterizedTest(name = "베팅 금액이 양수가 아닌 경우 예외 발생")
    @ValueSource(strings = {"0", "-1"})
    void checkNotPositiveNumbeer(String value) {
        assertThatThrownBy(() -> new BettingAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 양수여야 합니다.");
    }

    @ParameterizedTest(name = "승부 결과에 따른 수익 반환 테스트")
    @CsvSource(value = {"BLACK_JACK_WIN,15000", "WIN,10000", "DRAW,0", "LOSE,-10000"})
    void calculateProfit(MatchResult matchResult, int expectedProfit) {
        BettingAmount bettingAmount = new BettingAmount(10000);

        assertThat(bettingAmount.calculateProfit(matchResult)).isEqualTo(expectedProfit);
    }
}