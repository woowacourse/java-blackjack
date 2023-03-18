package blackjack.domain;

import blackjack.domain.result.Result;
import blackjack.domain.user.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BetAmountTest {

    @DisplayName("배팅 금액을 갖는다.")
    @Test
    void generateBetAmount() {
        // given
        int amount = 10000;

        // when & then
        assertDoesNotThrow(() -> new BetAmount(amount));
    }

    @DisplayName("배팅 금액이 500원 이하 1,000,000원 이상일 경우 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 50, 1000001, 10000000})
    void validateBetAmountRange(int amount) {
        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BetAmount(amount));
    }

    @DisplayName("실제로 받을 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"WIN:10000", "LOSE:-10000", "TIE:10000"}, delimiter = ':')
    void calculate_receiving_amount(String resultName, String expectedReceivingAmountValue) {
        // given
        BetAmount betAmount = new BetAmount(10_000);
        Result result = Result.valueOf(resultName);
        double expectedReceivingAmount = Integer.parseInt(expectedReceivingAmountValue);

        // when
        betAmount.updateReceivingAmount(result);

        // then
        assertThat(betAmount.getReceivingAmount()).isEqualTo(expectedReceivingAmount);
    }

    @Test
    @DisplayName("블랙잭이라면 베팅 금액의 1.5배를 받는다.")
    void calculate_blackjack() {
        // given
        int betAmountMoney = 10000;
        BetAmount betAmount = new BetAmount(betAmountMoney);

        // when
        betAmount.calculateBlackjack();

        // then
        assertThat(betAmount.getReceivingAmount())
                .isEqualTo(betAmountMoney * 1.5);
    }
}
