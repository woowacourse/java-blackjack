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
        assertThat(BettingAmount.newByDefault()).isNotNull();
    }

    @ParameterizedTest(name = "베팅 금액이 양수가 아닌 경우 예외 발생")
    @ValueSource(strings = {"0", "-1"})
    void checkNotPositiveNumber(int value) {
        BettingAmount bettingAmount = BettingAmount.newByDefault();

        assertThatThrownBy(() -> bettingAmount.startBetting(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0 이상이어야 합니다.");
    }

    @ParameterizedTest(name = "승부 결과에 따른 수익 반환 테스트")
    @CsvSource(value = {"BLACK_JACK_WIN,15000", "WIN,10000", "DRAW,0", "LOSE,-10000"})
    void calculateProfit(BlackJackMatch blackJackMatch, int expectedProfit) {
        BettingAmount bettingAmount = BettingAmount.newByDefault();

        assertThat(bettingAmount.startBetting(10000).calculateProfit(blackJackMatch)).isEqualTo(expectedProfit);
    }
}